package com.tanya.currency_exchange_api.transactions.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ConvertResponse(
        UUID transactionId,
        BigDecimal amount
) {
}
