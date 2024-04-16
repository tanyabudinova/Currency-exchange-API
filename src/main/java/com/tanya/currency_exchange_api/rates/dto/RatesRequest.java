package com.tanya.currency_exchange_api.rates.dto;

public record RatesRequest(
        String sourceCurrency,
        String targetCurrency
) {}
