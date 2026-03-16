package com.brasilprev.orders.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Schema(description = "Order entity representing a customer order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the order", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Name of the customer who placed the order", example = "John Silva")
    private String customerName;

    @Column(nullable = false)
    @Schema(description = "Current status of the order", example = "PENDING",
            allowableValues = {"PENDING", "APPROVED", "CANCELLED"})
    private String status;

    @Column(nullable = false)
    @Schema(description = "Total amount of the order", example = "150.00")
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @Schema(description = "Date and time when the order was created", example = "2026-03-15T03:01:17")
    private LocalDateTime createdAt;

    public Order() {}

    public Order(String customerName, String status, BigDecimal totalAmount) {
        this.customerName = customerName;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getStatus() { return status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setStatus(String status) { this.status = status; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
