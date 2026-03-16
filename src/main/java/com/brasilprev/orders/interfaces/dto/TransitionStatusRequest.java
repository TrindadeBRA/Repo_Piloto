package com.brasilprev.orders.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request to transition order status")
public class TransitionStatusRequest {

    @NotBlank(message = "Status é obrigatório")
    @Schema(description = "Target status for the transition", example = "CONFIRMADO")
    private String status;

    public TransitionStatusRequest() {}

    public TransitionStatusRequest(String status) {
        this.status = status;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
