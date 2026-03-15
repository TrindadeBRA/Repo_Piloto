package com.brasilprev.orders.interfaces.controller;

import com.brasilprev.orders.application.usecase.CreateOrderUseCase;
import com.brasilprev.orders.application.usecase.DeleteOrderUseCase;
import com.brasilprev.orders.application.usecase.FindOrderUseCase;
import com.brasilprev.orders.application.usecase.ListOrdersUseCase;
import com.brasilprev.orders.application.usecase.UpdateOrderUseCase;
import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.interfaces.dto.CreateOrderRequest;
import com.brasilprev.orders.interfaces.dto.UpdateOrderRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/without-steering/orders")
@Tag(name = "Without Steering", description = "Endpoints gerados SEM steering — sem ApiResponse wrapper, sem versionamento, sem documentação detalhada")
public class WithoutSteeringOrderController {

    private final ListOrdersUseCase listOrdersUseCase;
    private final FindOrderUseCase findOrderUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    public WithoutSteeringOrderController(ListOrdersUseCase listOrdersUseCase,
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
    public List<Order> list() {
        return listOrdersUseCase.execute();
    }

    @GetMapping("/{id}")
    public Order find(@PathVariable Long id) {
        return findOrderUseCase.execute(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody CreateOrderRequest request) {
        return createOrderUseCase.execute(request);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @Valid @RequestBody UpdateOrderRequest request) {
        return updateOrderUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteOrderUseCase.execute(id);
    }
}
