package com.brasilprev.orders.interfaces.controller;

import com.brasilprev.orders.application.usecase.ListOrdersUseCase;
import com.brasilprev.orders.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final ListOrdersUseCase listOrdersUseCase;

    public OrderController(ListOrdersUseCase listOrdersUseCase) {
        this.listOrdersUseCase = listOrdersUseCase;
    }

    @GetMapping
    public Page<Order> list(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
        return listOrdersUseCase.execute(PageRequest.of(page, size));
    }
}