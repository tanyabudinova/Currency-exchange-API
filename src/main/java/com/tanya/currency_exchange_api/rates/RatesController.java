package com.tanya.currency_exchange_api.rates;

import com.tanya.currency_exchange_api.rates.dto.RatesRequest;
import com.tanya.currency_exchange_api.rates.dto.RatesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange-rate")
public class RatesController {

    RatesService ratesService;

    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @GetMapping
    public RatesResponse exchangeRate(RatesRequest ratesRequest) {
        return ratesService.getExchangeRate(ratesRequest.sourceCurrency(),
                                            ratesRequest.targetCurrency());
    }

}
