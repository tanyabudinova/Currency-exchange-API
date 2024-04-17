package com.tanya.currency_exchange_api.transactions.dto;

import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.UUID;

public record HistoryRequest(
        @Nullable
        UUID transactionId,
        @Nullable
        LocalDate date,
        @Nullable
        Integer page,
        @Nullable
        Integer pageSize
) {
}
