package com.tanya.currency_exchange_api.rates;

import com.tanya.currency_exchange_api.rates.dto.RatesResponse;

public interface RatesService {
    RatesResponse getExchangeRate(String source, String target);
}
