package com.tanya.currency_exchange_api.pollerTests;

import com.tanya.currency_exchange_api.poller.ApiFetcherImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApiFetcherImplTest {

    @InjectMocks
    ApiFetcherImpl apiFetcher;

    @Test
    void fetchData() {
    }
}