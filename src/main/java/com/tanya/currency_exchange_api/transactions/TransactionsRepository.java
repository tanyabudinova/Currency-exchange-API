package com.tanya.currency_exchange_api.transactions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionsRepository extends JpaRepository<TransactionEntity, UUID>{
    List<TransactionEntity> findByTimestampBetween(LocalDateTime starOfDay, LocalDateTime endOfDay,
                                                   Pageable pageable);
}
