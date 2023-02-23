package com.noob.resourcewebfluxserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ResourceWebfluxServerApplication {

    // /httptrace 改为 /httpexchanges
    @Bean
    public HttpExchangeRepository httpTraceRepository() {
        return new InMemoryHttpExchangeRepository();
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceWebfluxServerApplication.class, args);
    }

}
