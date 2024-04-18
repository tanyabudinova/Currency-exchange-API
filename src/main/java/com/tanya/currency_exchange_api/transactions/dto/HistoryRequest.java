package com.tanya.currency_exchange_api.transactions.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.UUID;

public record HistoryRequest(
        @Nullable
        @Parameter(description = "The transaction id to get a single transaction.", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID transactionId,
        @Nullable
        @Parameter(description = "The date to get transactions for. Should be in the format yyyy-MM-dd.", example = "2021-12-31")
        LocalDate date,
        @Nullable
        @Min(0)
        @DefaultValue("0")
        @Parameter(description = "The page number to get. Starts from 0.", example = "0")
        Integer page,
        @Nullable
        @Min(1)
        @DefaultValue("5")
        @Parameter(description = "The number of transactions per page.", example = "5")
        Integer pageSize
) {}
