package com.tanya.currency_exchange_api.poller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PollerConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
