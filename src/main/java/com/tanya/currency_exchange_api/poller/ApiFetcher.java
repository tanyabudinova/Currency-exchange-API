package com.tanya.currency_exchange_api.poller;

import com.tanya.currency_exchange_api.rates.Rate;

import java.util.List;

public interface ApiFetcher {
    List<Rate> fetchData();
}
