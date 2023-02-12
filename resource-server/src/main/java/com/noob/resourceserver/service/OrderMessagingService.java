package com.noob.resourceserver.service;

import com.noob.resourceserver.model.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
