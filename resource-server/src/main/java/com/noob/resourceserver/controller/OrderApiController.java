package com.noob.resourceserver.controller;

import com.noob.commons.model.TacoOrder;
import com.noob.resourceserver.service.OrderMessagingService;
import com.noob.commons.dao.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
//@CrossOrigin(origins = "http://localhost:8080")
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderMessagingService messagingService;

    public OrderApiController(
            OrderRepository orderRepository,
            OrderMessagingService messagingService
    ) {
        this.orderRepository = orderRepository;
        this.messagingService = messagingService;
    }

    @GetMapping
    public ResponseEntity<String> testSendOrder() {
        messagingService.sendTest();

        return ResponseEntity.ok("Success.");
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        messagingService.sendOrder(order);

        return orderRepository.save(order);
    }
}
