package com.noob.tacocloud.dao;

import com.noob.tacocloud.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
