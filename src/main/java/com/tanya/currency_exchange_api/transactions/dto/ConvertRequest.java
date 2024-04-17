package com.tanya.currency_exchange_api.transactions.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record ConvertRequest(
        @NotBlank
        @Min(0)
        BigDecimal amount,
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}", message = "The source currency should be 3 capital letters")
        String sourceCurrency,
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}", message = "The source currency should be 3 capital letters")
        String targetCurrency
) {
}
