package com.tanya.currency_exchange_api.transactions;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal sourceAmount;
    private Double rate;
    private String sourceCurrency;
    private String targetCurrency;
    private LocalDateTime timestamp;

    public TransactionEntity() {}

    public TransactionEntity(UUID id, BigDecimal sourceAmount, Double rate, String sourceCurrency, String targetCurrency, LocalDateTime timestamp) {
        this.id = id;
        this.sourceAmount = sourceAmount;
        this.rate = rate;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    public Double getRate() {
        return rate;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public TransactionEntity setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
        return this;
    }

    public TransactionEntity setRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public TransactionEntity setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
        return this;
    }

    public TransactionEntity setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
        return this;
    }

    public TransactionEntity setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionEntity entity = (TransactionEntity) o;

        if (!Objects.equals(id, entity.id)) return false;
        if (!sourceAmount.equals(entity.sourceAmount)) return false;
        if (!rate.equals(entity.rate)) return false;
        if (!sourceCurrency.equals(entity.sourceCurrency)) return false;
        if (!targetCurrency.equals(entity.targetCurrency)) return false;
        return timestamp.equals(entity.timestamp);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + sourceAmount.hashCode();
        result = 31 * result + rate.hashCode();
        result = 31 * result + sourceCurrency.hashCode();
        result = 31 * result + targetCurrency.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
