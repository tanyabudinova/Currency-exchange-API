package com.tanya.currency_exchange_api.utils;

import org.springframework.http.HttpStatus;

public record ApiError(
    HttpStatus status,
    String message
) {}