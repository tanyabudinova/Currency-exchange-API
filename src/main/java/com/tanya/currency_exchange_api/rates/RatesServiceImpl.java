package com.tanya.currency_exchange_api.rates;

import com.tanya.currency_exchange_api.rates.dto.RatesResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RatesServiceImpl implements RatesService {

    private final RatesRepository ratesRepository;

    public RatesServiceImpl(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    @Override
    public Optional<RatesResponse> getExchangeRate(String source, String target) {
        RateEntity.RatesId id = new RateEntity.RatesId(source, target);
        return ratesRepository.findById(id).map(x -> new RatesResponse(x.getRate()));
    }

    @Override
    public void saveListOfRates(List<Rate> rates) {
        List<RateEntity> entities = rates.stream().map(rate -> {
            RateEntity.RatesId id = new RateEntity.RatesId(rate.source(), rate.target());
            return new RateEntity(id, rate.rate());
        }).toList();
        ratesRepository.saveAllAndFlush(entities);
    }

}
