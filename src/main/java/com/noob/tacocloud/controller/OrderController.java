package com.noob.tacocloud.controller;

import com.noob.tacocloud.dao.OrderRepository;
import com.noob.tacocloud.model.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid TacoOrder order,
            Errors errors,
            SessionStatus sessionStatus
    ) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        orderRepository.save(order);
        // 清理会话
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
