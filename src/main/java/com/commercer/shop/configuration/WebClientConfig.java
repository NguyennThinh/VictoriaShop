package com.commercer.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private WebClient webClient;

    @Value("${rest.url}")
    private String restUrl;

    @Bean
    public WebClient webClient(){
        webClient = WebClient.create(restUrl);
     ;
        return webClient;
    }
}
