package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.model.OrderStatus;
import com.brasilprev.orders.domain.repository.OrderRepository;
import com.brasilprev.orders.interfaces.dto.UpdateOrderRequest;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderUseCase {

    private final OrderRepository orderRepository;

    public UpdateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Long id, UpdateOrderRequest request) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));

        existingOrder.setStatus(OrderStatus.fromString(request.getStatus()));
        
        return orderRepository.save(existingOrder);
    }
}