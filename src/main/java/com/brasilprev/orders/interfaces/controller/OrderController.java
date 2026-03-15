package com.brasilprev.orders.interfaces.controller;

import com.brasilprev.orders.application.usecase.FindOrderUseCase;
import com.brasilprev.orders.application.usecase.ListOrdersUseCase;
import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.interfaces.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final ListOrdersUseCase listOrdersUseCase;
    private final FindOrderUseCase findOrderUseCase;

    public OrderController(ListOrdersUseCase listOrdersUseCase,
                          FindOrderUseCase findOrderUseCase) {
        this.listOrdersUseCase = listOrdersUseCase;
        this.findOrderUseCase = findOrderUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> list() {
        List<Order> orders = listOrdersUseCase.execute();
        return ResponseEntity.ok(ApiResponse.ok(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> find(@PathVariable Long id) {
        Order order = findOrderUseCase.execute(id);
        return ResponseEntity.ok(ApiResponse.ok(order));
    }
}