package com.tanya.currency_exchange_api.transactions.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record ConvertRequest(
        @NotBlank
        @Min(0)
        @Parameter(description = "The amount to convert. Should be a positive number.", example = "100")
        BigDecimal amount,
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}", message = "The source currency should be 3 capital letters")
        @Parameter(description = "The source currency to convert from.", example = "USD")
        String sourceCurrency,
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}", message = "The source currency should be 3 capital letters")
        @Parameter(description = "The target currency to convert to.", example = "EUR")
        String targetCurrency
) {
}
