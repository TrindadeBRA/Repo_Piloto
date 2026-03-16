package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrderUseCase {

    private final OrderRepository orderRepository;

    public DeleteOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(Long id) {
        if (!orderRepository.findById(id).isPresent()) {
            throw new RuntimeException("Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }
}
