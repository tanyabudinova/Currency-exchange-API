package com.tanya.currency_exchange_api.rates;

import com.tanya.currency_exchange_api.rates.dto.RatesResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RatesServiceImpl implements RatesService {

    public static Map<String, Map<String, Double>> rates;

    public RatesServiceImpl() {
        rates = new HashMap<>();
        Map<String, Double> values = new HashMap<>();
        values.put("CAD", 1.26);
        values.put("CHF", 0.98);
        rates.put("USD", values);
    }

    @Override
    public RatesResponse getExchangeRate(String source, String target) {
        return new RatesResponse(rates.get(source).get(target));
    }
}
