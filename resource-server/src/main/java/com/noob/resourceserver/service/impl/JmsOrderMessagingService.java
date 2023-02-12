package com.noob.resourceserver.service.impl;

import com.noob.resourceserver.model.TacoOrder;
import com.noob.resourceserver.service.OrderMessagingService;
import jakarta.jms.Destination;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jms;
    private final Destination orderQueue;

    public JmsOrderMessagingService(
            JmsTemplate jms,
            Destination orderQueue
    ) {
        this.jms = jms;
        this.orderQueue = orderQueue;
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", TacoOrder.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jms.convertAndSend(orderQueue, order, message -> {
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }

}
