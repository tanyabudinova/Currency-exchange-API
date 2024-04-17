package com.tanya.currency_exchange_api.transactions.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.UUID;

public record HistoryRequest(
        @Nullable
        UUID transactionId,
        @Nullable
        LocalDate date,
        @Nullable
        @Min(0)
        @DefaultValue("0")
        Integer page,
        @Nullable
        @Min(1)
        @DefaultValue("5")
        Integer pageSize
) {}
