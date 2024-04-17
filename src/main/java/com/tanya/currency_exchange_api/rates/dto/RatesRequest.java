package com.tanya.currency_exchange_api.rates.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RatesRequest(
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}", message = "The source currency should be 3 capital letters")
        String sourceCurrency,
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}", message = "The target currency should be 3 capital letters")
        String targetCurrency
) {}
