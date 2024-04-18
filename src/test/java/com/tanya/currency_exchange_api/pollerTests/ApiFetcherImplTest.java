package com.tanya.currency_exchange_api.pollerTests;

import com.tanya.currency_exchange_api.poller.ApiFetcherImpl;
import com.tanya.currency_exchange_api.rates.Rate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiFetcherImplTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ApiFetcherImpl apiFetcher;

    @Test
    void shouldReturnListOfRateWhenCalled() {
        ReflectionTestUtils.setField(apiFetcher, "apiUrl", "https://api.com");
        String source = "USD";
        String target = "EUR";
        Double rate = 0.8;
        ResponseEntity<ApiFetcherImpl.JsonData> response = new ResponseEntity<>
                (new ApiFetcherImpl.JsonData(Map.of(source, new ApiFetcherImpl.JsonData.CurrencyRate(target, rate))), HttpStatus.OK);
        List<String> currencies = new ArrayList<>(Arrays.asList("USD", "BGN", "CNY", "EUR", "ZAR"));
        List<Rate> expected = new ArrayList<>();
        for (String currency : currencies) {
            expected.add(new Rate(currency, target, rate));
        }

        when(restTemplate.exchange(anyString(), any(), any(), any(Class.class)))
                .thenReturn(response);

        assertEquals(expected, apiFetcher.fetchData());
    }

    @Test
    void shouldReturnEmptyListWhenResponseIsInvalid() {
        ReflectionTestUtils.setField(apiFetcher, "apiUrl", "https://api.com");
        ResponseEntity<ApiFetcherImpl.JsonData> response = new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        List<Rate> expected = new ArrayList<>();

        when(restTemplate.exchange(anyString(), any(), any(), any(Class.class)))
                .thenReturn(response);

        assertIterableEquals(expected, apiFetcher.fetchData());
    }

}