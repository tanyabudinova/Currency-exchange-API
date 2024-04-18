package com.tanya.currency_exchange_api.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionDTO {
    private UUID id;
    private BigDecimal sourceAmount;
    private Double rate;
    private String sourceCurrency;
    private String targetCurrency;
    private LocalDateTime timestamp;


    public TransactionDTO() {}

    public TransactionDTO(UUID id, BigDecimal sourceAmount, Double rate, String sourceCurrency, String targetCurrency, LocalDateTime timestamp) {
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

    public TransactionDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    public TransactionDTO setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
        return this;
    }

    public Double getRate() {
        return rate;
    }

    public TransactionDTO setRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public TransactionDTO setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
        return this;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public TransactionDTO setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionDTO setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionDTO that = (TransactionDTO) o;

        if (!id.equals(that.id)) return false;
        if (!sourceAmount.equals(that.sourceAmount)) return false;
        if (!rate.equals(that.rate)) return false;
        if (!sourceCurrency.equals(that.sourceCurrency)) return false;
        if (!targetCurrency.equals(that.targetCurrency)) return false;
        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + sourceAmount.hashCode();
        result = 31 * result + rate.hashCode();
        result = 31 * result + sourceCurrency.hashCode();
        result = 31 * result + targetCurrency.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
