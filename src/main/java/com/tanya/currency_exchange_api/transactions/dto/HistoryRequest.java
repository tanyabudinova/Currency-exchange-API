package com.tanya.currency_exchange_api.transactions.dto;

import java.time.LocalDate;

public record HistoryRequest(
        Integer transactionId,
        LocalDate date
) {
}
