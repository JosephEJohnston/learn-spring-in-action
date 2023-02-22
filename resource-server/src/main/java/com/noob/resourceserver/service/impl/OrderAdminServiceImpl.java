package com.noob.resourceserver.service.impl;

import com.noob.resourceserver.service.OrderAdminService;
import com.noob.commons.dao.OrderRepository;
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
