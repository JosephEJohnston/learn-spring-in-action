package com.noob.tacocloud.dao.impl;

import com.noob.tacocloud.dao.OrderAdminService;
import com.noob.tacocloud.dao.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class OrderAdminServiceImpl implements OrderAdminService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
