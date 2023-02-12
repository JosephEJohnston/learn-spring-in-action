package com.noob.resourceserver.handler;

import com.noob.resourceserver.model.TacoOrder;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder order) {
        System.out.println(order);
    }
}
