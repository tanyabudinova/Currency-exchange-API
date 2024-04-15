package com.tanya.currency_exchange_api.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        Integer id,
        BigDecimal sourceAmount,
        Double rate,
        String sourceCurrency,
        String targetCurrency,
        LocalDateTime timestamp
) {}
