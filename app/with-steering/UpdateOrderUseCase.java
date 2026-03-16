package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.repository.OrderRepository;
import com.brasilprev.orders.interfaces.dto.UpdateOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(UpdateOrderUseCase.class);

    private final OrderRepository orderRepository;

    public UpdateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Long id, UpdateOrderRequest request) {
        log.info("Updating order id: {}", id);

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));

        existingOrder.setCustomerName(request.getCustomerName());
        existingOrder.setStatus(request.getStatus());
        existingOrder.setTotalAmount(request.getTotalAmount());

        Order updated = orderRepository.save(existingOrder);
        log.info("Order updated id: {}", updated.getId());
        return updated;
    }
}
