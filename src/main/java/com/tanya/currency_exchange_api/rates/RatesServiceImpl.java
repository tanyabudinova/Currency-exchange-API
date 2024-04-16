package com.tanya.currency_exchange_api.rates;

import com.tanya.currency_exchange_api.rates.dto.RatesResponse;
import com.tanya.currency_exchange_api.utils.MissingRateException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RatesServiceImpl implements RatesService {

    private final RatesRepository ratesRepository;

    public RatesServiceImpl(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    @Override
    public RatesResponse getExchangeRate(String source, String target) throws MissingRateException {
        RateEntity.RateId id = new RateEntity.RateId(source, target);
        Optional<RatesResponse> responseOpt = ratesRepository.findById(id)
                .map(x -> new RatesResponse(x.getRate()));
        return responseOpt.orElseThrow(() -> new MissingRateException(source, target));

    }

    @Override
    public void saveListOfRates(List<Rate> rates) {
        List<RateEntity> entities = rates.stream().map(rate -> {
            RateEntity.RateId id = new RateEntity.RateId(rate.source(), rate.target());
            return new RateEntity(id, rate.rate());
        }).toList();
        ratesRepository.saveAllAndFlush(entities);
    }

}
