package com.tanya.currency_exchange_api.utils;

import java.util.function.Supplier;

public class MissingRateException extends RuntimeException {
    public MissingRateException(String source, String target) {
        super("The rate from " + source + " to " + target + " is missing in the DB");
    }
}
