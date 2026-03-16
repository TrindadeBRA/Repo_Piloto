package com.brasilprev.orders.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Request to update an existing order")
public class UpdateOrderRequest {

    @NotBlank(message = "Customer name is required")
    @Size(max = 255, message = "Customer name must not exceed 255 characters")
    @Schema(description = "Name of the customer", example = "John Silva Updated")
    private String customerName;

    @NotBlank(message = "Status is required")
    @Schema(description = "Order status", example = "APPROVED", allowableValues = {"PENDING", "APPROVED", "CANCELLED"})
    private String status;

    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be positive")
    @Schema(description = "Total amount of the order", example = "200.00")
    private BigDecimal totalAmount;

    public UpdateOrderRequest() {}

    public UpdateOrderRequest(String customerName, String status, BigDecimal totalAmount) {
        this.customerName = customerName;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
