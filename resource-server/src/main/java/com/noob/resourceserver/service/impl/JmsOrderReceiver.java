package com.noob.resourceserver.service.impl;

import com.noob.resourceserver.model.TacoOrder;
import com.noob.resourceserver.service.OrderReceiver;
import jakarta.jms.Destination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsOrderReceiver implements OrderReceiver {

    private final JmsTemplate jms;
    private final Destination destination;

    public JmsOrderReceiver(
            JmsTemplate jms,
            Destination destination
    ) {
        this.jms = jms;
        this.destination = destination;
    }

    @Override
    public TacoOrder receiverOrder() {
        return (TacoOrder) jms.receiveAndConvert(destination);
    }
}
