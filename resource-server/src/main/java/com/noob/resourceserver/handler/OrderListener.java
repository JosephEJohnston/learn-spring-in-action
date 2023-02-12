package com.noob.resourceserver.handler;

import com.noob.resourceserver.model.TacoOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder order) {
        System.out.println(order);
    }
}
