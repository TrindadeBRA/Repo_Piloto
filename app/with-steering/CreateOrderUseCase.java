package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.repository.OrderRepository;
import com.brasilprev.orders.interfaces.dto.CreateOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateOrderUseCase.class);

    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(CreateOrderRequest request) {
        log.info("Creating order for customer: {}", request.getCustomerName());

        Order order = new Order(
            request.getCustomerName(),
            request.getStatus(),
            request.getTotalAmount()
        );

        Order saved = orderRepository.save(order);
        log.info("Order created with id: {}", saved.getId());
        return saved;
    }
}
