package com.amcom.orderservice.dto;


import java.util.List;

public class OrderDTO {

    private Long id;
    private List<OrderItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(Long id, List<OrderItemDTO> items) {
        this.id = id;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
