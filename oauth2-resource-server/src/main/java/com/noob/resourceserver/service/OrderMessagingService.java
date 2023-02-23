package com.noob.resourceserver.service;

import com.noob.commons.model.TacoOrder;

public interface OrderMessagingService {

    void sendTest();

    void sendOrder(TacoOrder order);
}
