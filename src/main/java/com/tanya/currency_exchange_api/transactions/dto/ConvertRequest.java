package com.tanya.currency_exchange_api.transactions.dto;

import java.math.BigDecimal;

public record ConvertRequest(
        BigDecimal amount,
        String sourceCurrency,
        String targetCurrency
) {
}
