package com.tanya.currency_exchange_api.rates.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RatesRequest(
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}", message = "The source currency should be 3 capital letters")
        @Parameter(description = "The source currency to convert from.", example = "USD")
        String sourceCurrency,
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}", message = "The target currency should be 3 capital letters")
        @Parameter(description = "The target currency to convert to.", example = "EUR")
        String targetCurrency
) {}
