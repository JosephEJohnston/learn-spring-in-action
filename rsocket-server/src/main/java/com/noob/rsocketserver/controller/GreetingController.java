package com.noob.rsocketserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class GreetingController {

    // 请求-响应通信模型
    @MessageMapping("greeting/{name}")
    public Mono<String> handleGreeting(
            @DestinationVariable("name") String name,
            Mono<String> greetingMono) {
        return greetingMono
                .doOnNext(greeting -> log.info("Received a greeting: {} : {}", name, greeting))
                .map(greeting -> "Hello back to you, too, " + name);
    }


}