package com.tanya.currency_exchange_api;

import java.time.LocalDate;

public record HistoryRequest(
        Long id,
        LocalDate date
) {
}
