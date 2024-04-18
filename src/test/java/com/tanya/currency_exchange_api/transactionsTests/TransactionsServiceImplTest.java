package com.tanya.currency_exchange_api.transactionsTests;

import com.tanya.currency_exchange_api.rates.RateEntity;
import com.tanya.currency_exchange_api.rates.RatesRepository;
import com.tanya.currency_exchange_api.transactions.TransactionEntity;
import com.tanya.currency_exchange_api.transactions.TransactionsRepository;
import com.tanya.currency_exchange_api.transactions.TransactionsServiceImpl;
import com.tanya.currency_exchange_api.transactions.dto.*;
import com.tanya.currency_exchange_api.utils.MissingRateException;
import com.tanya.currency_exchange_api.utils.MissingTransactionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionsServiceImplTest {

    @Mock
    RatesRepository ratesRepository;

    @Mock
    TransactionsRepository transactionsRepository;

    @InjectMocks
    TransactionsServiceImpl transactionsService;

    //tests for createTransactionFunction
    @Test
    void shouldThrowErrorWhenThereIsNoRate() {
        BigDecimal amount = BigDecimal.valueOf(5);
        String source = "USD";
        String target = "BGN";
        ConvertRequest request = new ConvertRequest(amount, source, target);
        RateEntity.RateId id = new RateEntity.RateId(source, target);
        Optional<RateEntity> entity = Optional.empty();

        when(ratesRepository.findById(id)).thenReturn(entity);

        assertThrows(MissingRateException.class, () -> transactionsService.createTransaction(request));

    }

    @Test
    void shouldReturnConvertResponseWhenAllIsOk() {
        BigDecimal amount = BigDecimal.valueOf(5);
        String source = "USD";
        String target = "BGN";
        Double rate = 3.0;
        ConvertRequest request = new ConvertRequest(amount, source, target);
        RateEntity.RateId id = new RateEntity.RateId(source, target);
        BigDecimal resultAmount = BigDecimal.valueOf(15.0);
        Optional<RateEntity> entity = Optional.of(new RateEntity(id, rate));
        TransactionEntity savedTransaction = new TransactionEntity();
        savedTransaction.setId(new UUID(1, 1));

        when(ratesRepository.findById(id)).thenReturn(entity);
        when(transactionsRepository.save(any())).thenReturn(savedTransaction);
        ConvertResponse result = transactionsService.createTransaction(request);

        verify(transactionsRepository, times(1)).save(any());
        assertEquals(resultAmount, result.amount());
    }

    //tests for getTransactions function
    @Test
    void shouldThrowErrorWhenThereIsNoTransactionWithTheId() {
        UUID id = new UUID(1, 1);
        HistoryRequest request = new HistoryRequest(id, null, null, null);
        Optional<TransactionEntity> entity = Optional.empty();

        when(transactionsRepository.findById(id)).thenReturn(entity);

        assertThrows(MissingTransactionException.class, () -> transactionsService.getTransactions(request));
    }

    @Test
    void shouldReturnOneTransactionWhenFindById() {
        UUID id = new UUID(1, 1);
        HistoryRequest request = new HistoryRequest(id, null, null, null);
        LocalDateTime date = LocalDateTime.now();
        TransactionEntity transaction = new TransactionEntity(id, BigDecimal.valueOf(5), 3.0,
                "USD", "BGN", date);
        Optional<TransactionEntity> entity = Optional.of(transaction);
        TransactionDTO dto = new TransactionDTO(id, BigDecimal.valueOf(5), 3.0,
                "USD", "BGN", date);
        List<TransactionDTO> dtoList = List.of(dto);
        HistoryResponse expected = new HistoryResponse(dtoList);

        when(transactionsRepository.findById(id)).thenReturn(entity);
        HistoryResponse result = transactionsService.getTransactions(request);

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnPagedTransactionsThatFitWhenFilteredByDate() {
        LocalDate date = LocalDate.of(2024, 4, 18);
        Integer page = 0;
        Integer size = 5;
        Pageable pageable = PageRequest.of(page, size);
        HistoryRequest request = new HistoryRequest(null, date, page, size);
        LocalDateTime time1 = LocalDateTime.of(2024, 4, 18, 1, 20);
        LocalDateTime time2 = LocalDateTime.of(2024, 4, 18, 23, 20);
        LocalDateTime start = LocalDateTime.of(2024, 4, 18, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 4, 18, 23, 59, 59, 999999999);
        UUID id = new UUID(1, 1);
        BigDecimal amount = BigDecimal.valueOf(5);
        Double rate = 5.0;
        String source = "USD";
        String target = "BGN";
        TransactionEntity entity1 = new TransactionEntity(id, amount, rate, source, target, time1);
        TransactionEntity entity2 = new TransactionEntity(id, amount, rate, source, target, time2);
        TransactionDTO dto1 = new TransactionDTO(id, amount, rate, source, target, time1);
        TransactionDTO dto2 = new TransactionDTO(id, amount, rate, source, target, time2);
        HistoryResponse expected = new HistoryResponse(List.of(dto1, dto2));

        when(transactionsRepository.findByTimestampBetweenOrderByTimestamp(start, end, pageable))
                .thenReturn(List.of(entity1, entity2));
        HistoryResponse result = transactionsService.getTransactions(request);

        assertEquals(expected, result);
    }

}