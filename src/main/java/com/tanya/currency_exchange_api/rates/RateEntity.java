package com.tanya.currency_exchange_api.rates;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "rates")
public class RateEntity {
    @EmbeddedId
    private RateId id;
    private Double rate;

    public RateEntity() {}

    public RateEntity(RateId id, Double rate) {
        this.id = id;
        this.rate = rate;
    }

    public RateId getId() {
        return id;
    }

    public Double getRate() {
        return rate;
    }

    public void setId(RateId id) {
        this.id = id;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Embeddable
    public static class RateId implements Serializable {
        private String source;
        private String target;

        public RateId() {}

        public RateId(String source, String target) {
            this.source = source;
            this.target = target;
        }

        public String getSource() {
            return source;
        }

        public String getTarget() {
            return target;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setTarget(String target) {
            this.target = target;
        }
    }
}
