package com.tanya.currency_exchange_api.rates;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RateEntity entity = (RateEntity) o;

        if (!id.equals(entity.id)) return false;
        return rate.equals(entity.rate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + rate.hashCode();
        return result;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RateId rateId = (RateId) o;

            if (!source.equals(rateId.source)) return false;
            return target.equals(rateId.target);
        }

        @Override
        public int hashCode() {
            int result = source.hashCode();
            result = 31 * result + target.hashCode();
            return result;
        }
    }
}
