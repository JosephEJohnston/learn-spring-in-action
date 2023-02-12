package com.noob.resourceserver.service.impl;

import com.noob.resourceserver.model.TacoOrder;
import com.noob.resourceserver.service.OrderMessagingService;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    @Resource
    private RabbitTemplate rabbit;

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public void sendOrder(TacoOrder order) {
        rabbit.convertAndSend("tacocloud.order", order, message -> {
            MessageProperties props = message.getMessageProperties();
            props.setHeader("X_ORDER_SOURCE", "WEB");

            return message;
        });
    }
}
