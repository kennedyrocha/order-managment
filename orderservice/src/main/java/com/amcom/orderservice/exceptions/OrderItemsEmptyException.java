package com.amcom.orderservice.exceptions;

public class OrderItemsEmptyException extends Exception {

    public OrderItemsEmptyException(String message) {
        super(message);
    }
}
