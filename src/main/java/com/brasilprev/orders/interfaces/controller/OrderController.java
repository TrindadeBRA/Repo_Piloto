package com.brasilprev.orders.interfaces.controller;

import com.brasilprev.orders.application.usecase.CreateOrderUseCase;
import com.brasilprev.orders.application.usecase.DeleteOrderUseCase;
import com.brasilprev.orders.application.usecase.FindOrderUseCase;
import com.brasilprev.orders.application.usecase.GetAllowedTransitionsUseCase;
import com.brasilprev.orders.application.usecase.ListOrdersUseCase;
import com.brasilprev.orders.application.usecase.TransitionOrderStatusUseCase;
import com.brasilprev.orders.application.usecase.UpdateOrderUseCase;
import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.model.OrderStatus;
import com.brasilprev.orders.interfaces.dto.ApiResponse;
import com.brasilprev.orders.interfaces.dto.CreateOrderRequest;
import com.brasilprev.orders.interfaces.dto.TransitionStatusRequest;
import com.brasilprev.orders.interfaces.dto.UpdateOrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders (Original)", description = "API original completa — endpoints de referência")
@io.swagger.v3.oas.annotations.Hidden
public class OrderController {

    private final ListOrdersUseCase listOrdersUseCase;
    private final FindOrderUseCase findOrderUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final TransitionOrderStatusUseCase transitionOrderStatusUseCase;
    private final GetAllowedTransitionsUseCase getAllowedTransitionsUseCase;

    public OrderController(ListOrdersUseCase listOrdersUseCase,
                          FindOrderUseCase findOrderUseCase,
                          CreateOrderUseCase createOrderUseCase,
                          UpdateOrderUseCase updateOrderUseCase,
                          DeleteOrderUseCase deleteOrderUseCase,
                          TransitionOrderStatusUseCase transitionOrderStatusUseCase,
                          GetAllowedTransitionsUseCase getAllowedTransitionsUseCase) {
        this.listOrdersUseCase = listOrdersUseCase;
        this.findOrderUseCase = findOrderUseCase;
        this.createOrderUseCase = createOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
        this.transitionOrderStatusUseCase = transitionOrderStatusUseCase;
        this.getAllowedTransitionsUseCase = getAllowedTransitionsUseCase;
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

    @PostMapping
    @Operation(summary = "Create new order", description = "Create a new order in the system")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Order created successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<Order>> create(@Valid @RequestBody CreateOrderRequest request) {
        Order order = createOrderUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(order));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order", description = "Update an existing order by its ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Order updated successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<Order>> update(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderRequest request) {
        Order order = updateOrderUseCase.execute(id, request);
        return ResponseEntity.ok(ApiResponse.ok(order));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order", description = "Delete an order by its ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Order deleted successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<Void>> delete(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id) {
        deleteOrderUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Transition order status", description = "Transition the status of an existing order")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Status transitioned successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid status value"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "422", description = "Invalid status transition")
    })
    public ResponseEntity<ApiResponse<Order>> transitionStatus(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id,
            @Valid @RequestBody TransitionStatusRequest request) {
        OrderStatus newStatus = OrderStatus.fromString(request.getStatus());
        Order order = transitionOrderStatusUseCase.execute(id, newStatus);
        return ResponseEntity.ok(ApiResponse.ok(order));
    }

    @GetMapping("/{id}/status/transitions")
    @Operation(summary = "Get allowed status transitions", description = "Get the list of allowed status transitions for an order")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved allowed transitions"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<ApiResponse<List<OrderStatus>>> getAllowedTransitions(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id) {
        List<OrderStatus> transitions = getAllowedTransitionsUseCase.execute(id);
        return ResponseEntity.ok(ApiResponse.ok(transitions));
    }
}