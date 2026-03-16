package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.model.OrderStatus;
import com.brasilprev.orders.domain.repository.OrderRepository;
import com.brasilprev.orders.interfaces.dto.CreateOrderRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(CreateOrderRequest request) {
        Order order = new Order(
            request.getCustomerName(),
            OrderStatus.PENDENTE,
            request.getTotalAmount()
        );
        
        return orderRepository.save(order);
    }
}