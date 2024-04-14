package com.tanya.currency_exchange_api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @PostMapping
    public ConvertRequest currencyConversion(@RequestBody ConvertRequest convertRequest) {
        return null;
    }

    @GetMapping
    public HistoryRequest conversionHistory(HistoryRequest historyRequest) {
        return historyRequest;
    }
}
