package com.noob.resourceserver.service.impl;

import com.noob.resourceserver.model.TacoOrder;
import com.noob.resourceserver.service.OrderMessagingService;
import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    @Resource
    private KafkaTemplate<String, TacoOrder> kafkaTemplate;

    @Override
    public void sendTest() {
        kafkaTemplate.sendDefault(TacoOrder.ofEmpty());
    }

    @Override
    public void sendOrder(TacoOrder order) {
        //kafkaTemplate.send("tacocloud.orders.topic", order);
        kafkaTemplate.sendDefault(order);
    }
}
