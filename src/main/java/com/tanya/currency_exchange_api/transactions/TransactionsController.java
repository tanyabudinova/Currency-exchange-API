package com.tanya.currency_exchange_api.transactions;

import com.tanya.currency_exchange_api.transactions.dto.ConvertRequest;
import com.tanya.currency_exchange_api.transactions.dto.HistoryRequest;
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
