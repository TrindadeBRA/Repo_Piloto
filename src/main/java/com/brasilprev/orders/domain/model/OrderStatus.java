package com.brasilprev.orders.domain.model;

import java.util.Map;
import java.util.Optional;

public enum OrderStatus {
    PENDENTE,
    CONFIRMADO,
    ENVIADO,
    ENTREGUE;

    private static final Map<OrderStatus, OrderStatus> TRANSITIONS = Map.of(
        PENDENTE, CONFIRMADO,
        CONFIRMADO, ENVIADO,
        ENVIADO, ENTREGUE
    );

    public boolean canTransitionTo(OrderStatus target) {
        return TRANSITIONS.containsKey(this) && TRANSITIONS.get(this) == target;
    }

    public Optional<OrderStatus> getNextStatus() {
        return Optional.ofNullable(TRANSITIONS.get(this));
    }

    public static OrderStatus fromString(String value) {
        try {
            return OrderStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException(
                "Status inválido: " + value + ". Valores permitidos: PENDENTE, CONFIRMADO, ENVIADO, ENTREGUE"
            );
        }
    }
}
