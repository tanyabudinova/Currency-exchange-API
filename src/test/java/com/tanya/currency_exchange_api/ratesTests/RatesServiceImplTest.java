package com.tanya.currency_exchange_api.ratesTests;

import com.tanya.currency_exchange_api.rates.Rate;
import com.tanya.currency_exchange_api.rates.RateEntity;
import com.tanya.currency_exchange_api.rates.RatesRepository;
import com.tanya.currency_exchange_api.rates.RatesServiceImpl;
import com.tanya.currency_exchange_api.rates.dto.RatesResponse;
import com.tanya.currency_exchange_api.utils.MissingRateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatesServiceImplTest {

    @Mock
    RatesRepository ratesRepository;

    @InjectMocks
    RatesServiceImpl ratesService;

    //tests for getExchangeRate function
    @Test
    void shouldReturnRatesResponseWhenItExistsInDB() {
        String source = "USD";
        String target = "BGN";
        RateEntity.RateId id = new RateEntity.RateId(source, target);
        Optional<RateEntity> entity = Optional.of(new RateEntity(id, 1.5));
        RatesResponse expected = new RatesResponse(1.5);

        when(ratesRepository.findById(id)).thenReturn(entity);
        RatesResponse result = ratesService.getExchangeRate(source, target);

        assertEquals(expected, result);
    }

    @Test
    void shouldThrowMissingRateExceptionWhenItExistsInDB() {
        String source = "USD";
        String target = "BGN";
        RateEntity.RateId id = new RateEntity.RateId(source, target);
        Optional<RateEntity> entity = Optional.empty();

        when(ratesRepository.findById(id)).thenReturn(entity);

        assertThrows(MissingRateException.class, () -> ratesService.getExchangeRate(source, target));
    }

    //tests for saveListOfRates function
    @Test
    void shouldSaveAllRatesInTheList() {
        String source = "USD";
        String target = "EUR";
        Double rate = 0.85;
        Double rate2 = 1.18;
        List<Rate> rates = Arrays.asList(
                new Rate(source, target, rate),
                new Rate(target, source, rate2)
        );
        List<RateEntity> entities = Arrays.asList(
                new RateEntity(new RateEntity.RateId(source, target), rate),
                new RateEntity(new RateEntity.RateId(target, source), rate2)
        );

        ratesService.saveListOfRates(rates);

        verify(ratesRepository, times(1)).saveAllAndFlush(entities);
    }
}