package com.noob.commons.dao;

import com.noob.commons.model.security.User;
import com.noob.commons.model.TacoOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByUserOrderByPlaceAtDesc(User user, Pageable pageable);
}
