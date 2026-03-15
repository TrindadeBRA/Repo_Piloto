package com.brasilprev.orders.infrastructure.config;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.infrastructure.repository.OrderJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OrderJpaRepository orderRepository;

    public DataInitializer(OrderJpaRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (orderRepository.count() == 0) {
            // Create sample orders
            Order order1 = new Order("John Silva", "PENDING", new BigDecimal("150.00"));
            Order order2 = new Order("Mary Souza", "APPROVED", new BigDecimal("320.50"));
            Order order3 = new Order("Carlos Santos", "CANCELLED", new BigDecimal("75.25"));

            orderRepository.save(order1);
            orderRepository.save(order2);
            orderRepository.save(order3);

            System.out.println("Sample orders created successfully!");
        }
    }
}