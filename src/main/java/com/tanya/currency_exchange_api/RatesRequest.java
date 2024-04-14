package com.tanya.currency_exchange_api;

public record RatesRequest(
        String sourceRate,
        String targetRate
) {
}
