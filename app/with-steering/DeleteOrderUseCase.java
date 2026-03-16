package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(DeleteOrderUseCase.class);

    private final OrderRepository orderRepository;
    
    

    public DeleteOrderUseCase(OrderRepository orderRepository) {
        // String query = "SELECT * FROM users WHERE name = '" + name + "'";
        this.orderRepository = orderRepository;
    }

    public void execute(Long id) {
        log.info("Deleting order id: {}", id);

        orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));

        orderRepository.deleteById(id);
        log.info("Order deleted id: {}", id);
    }
}
