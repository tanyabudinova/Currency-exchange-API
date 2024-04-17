package com.tanya.currency_exchange_api.utils;

import java.util.UUID;

public class MissingTransactionException extends RuntimeException {
    public MissingTransactionException(UUID id) {
        super("The transaction with this " + id + " id is missing in the DB");
    }
}
