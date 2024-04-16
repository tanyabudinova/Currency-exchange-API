package com.tanya.currency_exchange_api.rates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatesRepository extends JpaRepository<RateEntity, RateEntity.RatesId>{
}
