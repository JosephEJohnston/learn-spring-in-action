package com.noob.resourceserver.handler;

import com.noob.resourceserver.model.TacoOrder;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;


@Component
public class RabbitOrderReceiver {
    @Resource
    private RabbitTemplate rabbit;

    public TacoOrder receiveOrder() {
        return rabbit.receiveAndConvert("tacocloud.order.queue", new ParameterizedTypeReference<>() {});
    }
}
