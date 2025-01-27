package com.amcom.orderservice.entity;

import com.amcom.orderservice.dto.OrderDTO;
import com.amcom.orderservice.enums.OrderStatus;
import jakarta.persistence.*;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="TB_ORDER")
public class Order implements Serializable {
    private static final long serialVersionUID = 6729389447594273304L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_id", length = 100, updatable = false)
    private String user;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @OneToMany(mappedBy="order")
    private Set<OrderItem> items = new HashSet<>();

    public Order() {
    }

    public Order(Long id, Double totalPrice, LocalDateTime createdAt, OrderStatus status, String user) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.status = status;
        this.user = user;
    }

    public void calcTotalPrice() {
        double total = 0;
        for (OrderItem it : items) {
            total += it.getTotalPrice();
        }
        this.totalPrice = total;
    }

    public Long getId() {
        return id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUser() {
        return user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(createdAt, order.createdAt) && Objects.equals(user, order.user) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, createdAt, user, status);
    }

    public static Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        if (!CollectionUtils.isEmpty(dto.getItems())) {
            order.setItems(dto.getItems().stream().map(OrderItem::toEntity).collect(Collectors.toSet()));
        }
        return order;
    }
}