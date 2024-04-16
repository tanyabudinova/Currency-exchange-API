package com.tanya.currency_exchange_api.rates;

import com.tanya.currency_exchange_api.rates.dto.RatesResponse;
import com.tanya.currency_exchange_api.utils.MissingRateException;

import java.util.List;

public interface RatesService {
    RatesResponse getExchangeRate(String source, String target) throws MissingRateException;
    void saveListOfRates(List<Rate> rates);
}
