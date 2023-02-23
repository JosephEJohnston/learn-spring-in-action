package com.noob.resourcewebfluxserver.dao;

import com.noob.resourcewebfluxserver.model.TacoOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<TacoOrder, Long> {
}
