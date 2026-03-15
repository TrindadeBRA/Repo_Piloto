package com.brasilprev.orders.interfaces.controller;

import com.brasilprev.orders.application.usecase.FindOrderUseCase;
import com.brasilprev.orders.application.usecase.ListOrdersUseCase;
import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.interfaces.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "Order management operations")
public class OrderController {

    private final ListOrdersUseCase listOrdersUseCase;
    private final FindOrderUseCase findOrderUseCase;

    public OrderController(ListOrdersUseCase listOrdersUseCase,
                          FindOrderUseCase findOrderUseCase) {
        this.listOrdersUseCase = listOrdersUseCase;
        this.findOrderUseCase = findOrderUseCase;
    }

    @GetMapping
    @Operation(summary = "List all orders", description = "Retrieve a list of all orders in the system")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<List<Order>>> list() {
        List<Order> orders = listOrdersUseCase.execute();
        return ResponseEntity.ok(ApiResponse.ok(orders));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find order by ID", description = "Retrieve a specific order by its unique identifier")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved the order"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<Order>> find(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id) {
        Order order = findOrderUseCase.execute(id);
        return ResponseEntity.ok(ApiResponse.ok(order));
    }
}