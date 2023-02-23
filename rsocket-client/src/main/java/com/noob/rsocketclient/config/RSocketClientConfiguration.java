package com.noob.rsocketclient.config;

import com.noob.rsocketcommon.model.Alert;
import com.noob.rsocketcommon.model.StockQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.time.Instant;

@Configuration
@Slf4j
public class RSocketClientConfiguration {

    @Bean
    public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            RSocketRequester tcp = requesterBuilder.tcp("localhost", 7000);

            String who = "Craig";
            tcp.route("greeting/{name}", who)
                    .data("Hello RSocket!")
                    .retrieveMono(String.class)
                    .subscribe(response -> log.info("Got a response: {}", response));

            String stockSymbol = "XYZ";
            tcp.route("stock/{symbol}", stockSymbol)
                    .retrieveFlux(StockQuote.class)
                    .doOnNext(stockQuote -> log.info(
                            "Price of {}: {} (at {})",
                            stockQuote.getSymbol(),
                            stockQuote.getPrice(),
                            stockQuote.getTimestamp()
                    ))
                    .subscribe();

            tcp.route("alert")
                    .data(new Alert(Alert.Level.RED, "Craig", Instant.now()))
                    .send()
                    .subscribe();

            log.info("Alert sent");
        };
    }

}
