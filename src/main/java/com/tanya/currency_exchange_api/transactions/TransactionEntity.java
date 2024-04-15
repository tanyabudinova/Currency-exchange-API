package com.tanya.currency_exchange_api.transactions;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private BigDecimal sourceAmount;
    private Double rate;
    private String sourceCurrency;
    private String targetCurrency;
    private LocalDateTime timestamp;

    public TransactionEntity() {}

    public TransactionEntity(Integer id, BigDecimal sourceAmount, Double rate, String sourceCurrency, String targetCurrency, LocalDateTime timestamp) {
        this.id = id;
        this.sourceAmount = sourceAmount;
        this.rate = rate;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.timestamp = timestamp;
    }

    public Integer getId() {
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

    public TransactionEntity setId(Integer id) {
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
}
