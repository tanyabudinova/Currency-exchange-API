package com.tanya.currency_exchange_api.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MissingRateException.class)
    public ResponseEntity<ApiError> handleException(MissingRateException e) {
        // TODO:log exception
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(new ApiError(status, message));
    }
}
