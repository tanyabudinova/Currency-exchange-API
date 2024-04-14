package com.tanya.currency_exchange_api;

public record ConvertRequest(
        Double amount,
        String sourceCurrency,
        String targetCurrency
) {
}
