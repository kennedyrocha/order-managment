package com.amcom.orderservice.service;

import com.amcom.orderservice.entity.OrderItem;
import com.amcom.orderservice.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public void save(OrderItem object) {
        repository.save(object);
    }
}
