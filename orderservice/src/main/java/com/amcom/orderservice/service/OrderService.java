package com.amcom.orderservice.service;

import com.amcom.orderservice.dto.OrderDTO;
import com.amcom.orderservice.entity.Order;
import com.amcom.orderservice.enums.OrderStatus;
import com.amcom.orderservice.exceptions.OrderItemsEmptyException;
import com.amcom.orderservice.repository.OrderRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Order not found", id));
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order createOrder(OrderDTO object) throws OrderItemsEmptyException {
        var order = Order.toEntity(object);
        if (CollectionUtils.isEmpty(order.getItems())) {
            throw new OrderItemsEmptyException("Item não informado para o Pedido");
        }
        calcOrderItemProduct(order);
        return saveOrder(order);
    }

    private void calcOrderItemProduct(Order order) {
        order.getItems().forEach(a -> {
            a.setProduct(productService.findById(a.getProduct().getId()));
            a.setTotalPrice(a.getQuantity() * a.getProduct().getPrice());
        });
        order.calcTotalPrice();
    }

    private Order saveOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDENTE);
        var orderEntity = repository.save(order);
        order.getItems().forEach(a -> {
            a.setOrder(orderEntity);
            orderItemService.save(a);
        });
        return orderEntity;
    }
}
