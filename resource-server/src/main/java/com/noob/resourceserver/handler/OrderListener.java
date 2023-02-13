package com.noob.resourceserver.handler;

import com.noob.resourceserver.model.TacoOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @KafkaListener(topics = "tacocloud.orders.topic", groupId = "taco.test")
    public void handle(TacoOrder order) {
        System.out.println(order);
    }
}
