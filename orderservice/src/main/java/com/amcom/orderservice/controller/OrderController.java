package com.amcom.orderservice.controller;

import com.amcom.orderservice.dto.OrderDTO;
import com.amcom.orderservice.entity.Order;
import com.amcom.orderservice.exceptions.OrderItemsEmptyException;
import com.amcom.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) throws OrderItemsEmptyException {
        return ResponseEntity.ok(service.createOrder(orderDTO));
    }
}
