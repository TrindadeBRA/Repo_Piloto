package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.model.OrderStatus;
import com.brasilprev.orders.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GetAllowedTransitionsUseCase {

    private final OrderRepository orderRepository;

    public GetAllowedTransitionsUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderStatus> execute(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        return order.getStatus().getNextStatus()
                .map(List::of)
                .orElse(Collections.emptyList());
    }
}
