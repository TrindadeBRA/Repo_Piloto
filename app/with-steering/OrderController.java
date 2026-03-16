package com.brasilprev.orders.interfaces.controller;

import com.brasilprev.orders.application.usecase.CreateOrderUseCase;
import com.brasilprev.orders.application.usecase.DeleteOrderUseCase;
import com.brasilprev.orders.application.usecase.FindOrderUseCase;
import com.brasilprev.orders.application.usecase.ListOrdersUseCase;
import com.brasilprev.orders.application.usecase.UpdateOrderUseCase;
import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.interfaces.dto.ApiResponse;
import com.brasilprev.orders.interfaces.dto.CreateOrderRequest;
import com.brasilprev.orders.interfaces.dto.UpdateOrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "Order management endpoints")
public class OrderController {

    private final ListOrdersUseCase listOrdersUseCase;
    private final FindOrderUseCase findOrderUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    public OrderController(ListOrdersUseCase listOrdersUseCase,
                           FindOrderUseCase findOrderUseCase,
                           CreateOrderUseCase createOrderUseCase,
                           UpdateOrderUseCase updateOrderUseCase,
                           DeleteOrderUseCase deleteOrderUseCase) {
        this.listOrdersUseCase = listOrdersUseCase;
        this.findOrderUseCase = findOrderUseCase;
        this.createOrderUseCase = createOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
    }

    @GetMapping
    @Operation(summary = "List all orders", description = "Returns a paginated list of orders")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    })
    public ResponseEntity<ApiResponse<Page<Order>>> list(Pageable pageable) {
        Page<Order> orders = listOrdersUseCase.execute(pageable);
        return ResponseEntity.ok(ApiResponse.ok(orders));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find order by ID", description = "Returns a single order by its ID")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Order found"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<ApiResponse<Order>> find(
            @Parameter(description = "Order ID", example = "1") @PathVariable Long id) {
        Order order = findOrderUseCase.execute(id);
        return ResponseEntity.ok(ApiResponse.ok(order));
    }

    @PostMapping
    @Operation(summary = "Create a new order", description = "Creates a new order and returns the created resource")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Order created successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<ApiResponse<Order>> create(@Valid @RequestBody CreateOrderRequest request) {
        Order created = createOrderUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing order", description = "Updates all fields of an existing order")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Order updated successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<ApiResponse<Order>> update(
            @Parameter(description = "Order ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody UpdateOrderRequest request) {
        Order updated = updateOrderUseCase.execute(id, request);
        return ResponseEntity.ok(ApiResponse.ok(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order", description = "Deletes an order by its ID")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Order deleted successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "Order ID", example = "1") @PathVariable Long id) {
        deleteOrderUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
