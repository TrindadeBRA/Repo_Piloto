package com.brasilprev.orders.domain.exception;

public class InvalidStatusTransitionException extends RuntimeException {

    private final String currentStatus;
    private final String requestedStatus;

    public InvalidStatusTransitionException(String currentStatus, String requestedStatus) {
        super("Transição de status inválida: " + currentStatus + " → " + requestedStatus);
        this.currentStatus = currentStatus;
        this.requestedStatus = requestedStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getRequestedStatus() {
        return requestedStatus;
    }
}
