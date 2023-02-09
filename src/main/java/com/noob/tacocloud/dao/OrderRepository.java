package com.noob.tacocloud.dao;

import com.noob.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

}
