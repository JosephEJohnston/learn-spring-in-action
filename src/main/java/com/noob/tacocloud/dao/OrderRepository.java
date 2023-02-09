package com.noob.tacocloud.dao;

import com.noob.tacocloud.model.TacoOrder;
import com.noob.tacocloud.model.security.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByUserOrderByPlaceAtDesc(User user, Pageable pageable);
}
