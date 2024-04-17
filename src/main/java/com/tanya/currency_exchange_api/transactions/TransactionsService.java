package com.tanya.currency_exchange_api.transactions;

import com.tanya.currency_exchange_api.transactions.dto.ConvertRequest;
import com.tanya.currency_exchange_api.transactions.dto.ConvertResponse;
import com.tanya.currency_exchange_api.transactions.dto.HistoryRequest;
import com.tanya.currency_exchange_api.transactions.dto.HistoryResponse;

import java.util.Optional;

public interface TransactionsService {
    ConvertResponse createTransaction(ConvertRequest convertRequest);
    HistoryResponse getTransactions(HistoryRequest historyRequest);
}
