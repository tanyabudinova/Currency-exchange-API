package com.tanya.currency_exchange_api;

import java.time.LocalDate;

public record TransactionDTO(
        Long id,
        Double amount,
        String sourceCurrency,
        String targetCurrency,
        LocalDate date
) {
}
