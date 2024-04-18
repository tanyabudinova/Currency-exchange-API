package com.tanya.currency_exchange_api.transactions.dto;

import java.util.List;

public record HistoryResponse(
        List<TransactionDTO> transactions
) {}
