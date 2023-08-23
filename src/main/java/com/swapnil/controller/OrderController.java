package com.swapnil.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swapnil.model.Order;
import com.swapnil.model.Product;
import com.swapnil.publisher.OrderPublisher;
import com.swapnil.publisher.ProductPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderPublisher orderPublisher;
    @GetMapping("/health/order")
    public String healthCheck(){
        return "I am Healthy!";
    }

    @PostMapping("/order")
    public Order publishOrder(@RequestBody Order order) {
        orderPublisher.publishOrderOnRabbit(order);
        return order;
    }
}
