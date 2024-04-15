package com.tanya.currency_exchange_api.transactions.dto;

import java.math.BigDecimal;

public record ConvertResponse(
        Integer transactionId,
        BigDecimal amount
) {
}
