package com.tanya.currency_exchange_api.rates;

public record Rate(
        String source,
        String target,
        Double rate
){}
