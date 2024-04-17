package com.tanya.currency_exchange_api.transactions;

import com.tanya.currency_exchange_api.transactions.dto.ConvertRequest;
import com.tanya.currency_exchange_api.transactions.dto.ConvertResponse;
import com.tanya.currency_exchange_api.transactions.dto.HistoryRequest;
import com.tanya.currency_exchange_api.transactions.dto.HistoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping
    public ResponseEntity<ConvertResponse> currencyConversion(@RequestBody ConvertRequest convertRequest) {
        ConvertResponse response = transactionsService.createTransaction(convertRequest);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<HistoryResponse> conversionHistory(HistoryRequest historyRequest) {
        HistoryResponse response = transactionsService.getTransactions(historyRequest);
        return ResponseEntity.ok()
                .body(response);
    }
}
