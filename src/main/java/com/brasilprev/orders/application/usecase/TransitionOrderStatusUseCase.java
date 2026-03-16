package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.exception.InvalidStatusTransitionException;
import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.model.OrderStatus;
import com.brasilprev.orders.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class TransitionOrderStatusUseCase {

    private final OrderRepository orderRepository;

    public TransitionOrderStatusUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        if (!order.getStatus().canTransitionTo(newStatus)) {
            throw new InvalidStatusTransitionException(order.getStatus().name(), newStatus.name());
        }

        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
}
