package com.tanya.currency_exchange_api.rates;

import com.tanya.currency_exchange_api.rates.dto.RatesResponse;

import java.util.List;
import java.util.Optional;

public interface RatesService {
    Optional<RatesResponse> getExchangeRate(String source, String target);
    void saveListOfRates(List<Rate> rates);
}
