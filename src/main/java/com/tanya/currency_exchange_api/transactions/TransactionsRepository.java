package com.tanya.currency_exchange_api.transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<TransactionEntity, Long>{
}
