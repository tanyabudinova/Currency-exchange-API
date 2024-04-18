package com.tanya.currency_exchange_api.poller;

import com.tanya.currency_exchange_api.rates.Rate;
import com.tanya.currency_exchange_api.rates.RatesService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledApiCall {

    private final RatesService ratesService;
    private final ApiFetcher apiFetcher;

    public ScheduledApiCall(RatesService ratesService, ApiFetcher apiFetcher) {
        this.ratesService = ratesService;
        this.apiFetcher = apiFetcher;
    }

    //@Scheduled(cron = "0 0 0 * * ?") for the purposes of this assignment
    //@Scheduled(fixedRate = 1000000)
    public void fetchData() {
        List<Rate> data = apiFetcher.fetchData();
        ratesService.saveListOfRates(data);
    }
}
