package com.tanya.currency_exchange_api.transactions;

import com.tanya.currency_exchange_api.rates.RateEntity;
import com.tanya.currency_exchange_api.rates.RatesRepository;
import com.tanya.currency_exchange_api.transactions.dto.*;
import com.tanya.currency_exchange_api.utils.MissingRateException;
import com.tanya.currency_exchange_api.utils.MissingTransactionException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class TransactionsServiceImpl implements TransactionsService {
    private final TransactionsRepository transactionsRepository;
    private final RatesRepository ratesRepository;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository,
                                   RatesRepository ratesRepository) {
        this.transactionsRepository = transactionsRepository;
        this.ratesRepository = ratesRepository;
    }

    @Override
    public ConvertResponse createTransaction(ConvertRequest convertRequest) {
        Double rate = getRate(convertRequest);
        TransactionEntity savedEntity = saveTransaction(convertRequest, rate);
        BigDecimal amount = convertRequest.amount().multiply(BigDecimal.valueOf(rate));
        return new ConvertResponse(savedEntity.getId(), amount);
    }

    @Override
    public HistoryResponse getTransactions(HistoryRequest historyRequest) {
        UUID id = historyRequest.transactionId();
        if(id != null) {
            return findById(id);
        } else {
            return filterByDate(historyRequest);
        }
    }

    private TransactionEntity saveTransaction(ConvertRequest convertRequest, Double rate) {
        TransactionEntity entity = new TransactionEntity();
        entity.setRate(rate)
                .setSourceCurrency(convertRequest.sourceCurrency())
                .setTargetCurrency(convertRequest.targetCurrency())
                .setTimestamp(LocalDateTime.now())
                .setSourceAmount(convertRequest.amount());
        return transactionsRepository.save(entity);
    }

    private Double getRate(ConvertRequest convertRequest) {
        RateEntity.RateId rateId = new RateEntity.RateId(
                convertRequest.sourceCurrency(),
                convertRequest.targetCurrency()
        );
        return ratesRepository.findById(rateId)
                .map(RateEntity::getRate)
                .orElseThrow(() -> new MissingRateException(
                        convertRequest.sourceCurrency(),
                        convertRequest.targetCurrency()));
    }

    private HistoryResponse filterByDate(HistoryRequest historyRequest) {
        Pageable pageable = PageRequest.of(historyRequest.page(), historyRequest.pageSize());
        LocalDateTime startOfDay = historyRequest.date().atStartOfDay();
        LocalDateTime endOfDay = historyRequest.date().atTime(LocalTime.MAX);
        List<TransactionEntity> transactions = transactionsRepository
                .findByTimestampBetween(startOfDay, endOfDay, pageable);
        List<TransactionDTO> DTOs = transactions.stream()
                .map(this::turnEntityToDTO).toList();
        return new HistoryResponse(DTOs);
    }

    private HistoryResponse findById(UUID id) {
        Optional<TransactionEntity> transaction = transactionsRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new MissingTransactionException(id);
        } else {
            TransactionEntity entity = transaction.get();
            TransactionDTO dto = turnEntityToDTO(entity);
            return new HistoryResponse(List.of(dto));
        }
    }

    private TransactionDTO turnEntityToDTO(TransactionEntity entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(entity.getId())
                .setRate(entity.getRate())
                .setSourceAmount(entity.getSourceAmount())
                .setTargetCurrency(entity.getTargetCurrency())
                .setTimestamp(entity.getTimestamp())
                .setSourceCurrency(entity.getSourceCurrency());
        return dto;
    }
}
