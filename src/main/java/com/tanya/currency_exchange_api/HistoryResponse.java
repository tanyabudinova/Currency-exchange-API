package com.tanya.currency_exchange_api;

import java.util.List;

public record HistoryResponse(
        List<TransactionDTO> transactions
) {
}
