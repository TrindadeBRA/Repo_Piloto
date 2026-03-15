package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListOrdersUseCase {

    private final OrderRepository orderRepository;

    public ListOrdersUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> execute() {
        return orderRepository.findAll();
    }
}