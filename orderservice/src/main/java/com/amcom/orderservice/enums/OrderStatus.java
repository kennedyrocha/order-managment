package com.amcom.orderservice.enums;

public enum OrderStatus {
    PENDENTE("Pendente"),
    SUCESSO("Sucesso"),
    ERRO("Erro");

    private final String text;

    OrderStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
