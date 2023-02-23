package com.noob.resourceserver.handler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @KafkaListener(topics = "tacocloud.orders.topic", groupId = "taco.test")
    public void handle(String msg) {
        System.out.println(msg);
    }
}
