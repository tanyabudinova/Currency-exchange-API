package com.tanya.currency_exchange_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange-rate")
public class RatesController {

    @GetMapping
    public Double exchangeRate(RatesRequest ratesRequest) {
        return 0.0;
    }

}
