package com.tanya.currency_exchange_api.rates;

import com.tanya.currency_exchange_api.rates.dto.RatesRequest;
import com.tanya.currency_exchange_api.rates.dto.RatesResponse;
import com.tanya.currency_exchange_api.utils.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/exchange-rate")
public class RatesController {

    private final RatesService ratesService;

    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @GetMapping
    public ResponseEntity<RatesResponse> exchangeRate(RatesRequest ratesRequest) {
        RatesResponse result = ratesService.getExchangeRate(ratesRequest.sourceCurrency(),
                                                                    ratesRequest.targetCurrency());
        return ResponseEntity.ok()
                .body(result);
    }
}
