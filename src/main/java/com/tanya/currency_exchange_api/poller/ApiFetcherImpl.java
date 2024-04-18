package com.tanya.currency_exchange_api.poller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tanya.currency_exchange_api.rates.Rate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ApiFetcherImpl implements ApiFetcher {

    @Value("${external.api.url}")
    private String apiUrl;

    //Doing it like this only for the purpose of the task
    @Value("${external.api.key}")
    private String apiKey;

    private final List<String> currencies;

    private final RestTemplate restTemplate;

    public ApiFetcherImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        //for the purposes of this assignment
        currencies = new ArrayList<>(Arrays.asList("USD", "BGN", "CNY", "EUR", "ZAR"));
    }

    @Override
    public List<Rate> fetchData() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        List<Rate> result = new ArrayList<>();
        for(String currency : currencies) {
            String url = buildUrl(currency);
            ResponseEntity<JsonData> response = restTemplate
                    .exchange(url, HttpMethod.GET, httpEntity, JsonData.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                if(response.hasBody()) {
                    //noinspection ConstantConditions
                    result.addAll(convertJsonToRate(response.getBody(), currency));
                }
            }
        }
        return result;
    }

    private List<Rate> convertJsonToRate(JsonData jsonData, String source) {
        return jsonData.data.values().stream()
                .map(rate -> new Rate(source, rate.code, rate.value)).toList();
    }

    private String buildUrl(String sourceCurrency) {
        String otherCurrencies = String.join(",", currencies.stream()
                .filter(currency -> !currency.equals(sourceCurrency)).toList());
        return UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment("latest")
                .queryParam("base_currency", sourceCurrency)
                .queryParam("currencies", otherCurrencies)
                .build().toUriString();
    }

    public static class JsonData {
        @JsonProperty
        public Map<String, CurrencyRate> data;

        public JsonData(Map<String, CurrencyRate> data) {
            this.data = data;
        }

        public static class CurrencyRate {
            @JsonProperty
            public String code;

            @JsonProperty
            public Double value;

            public CurrencyRate(String code, Double value) {
                this.code = code;
                this.value = value;
            }
        }
    }
}
