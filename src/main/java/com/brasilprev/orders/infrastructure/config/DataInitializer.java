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
            orderRepository.save(new Order("João Silva", "PENDING", new BigDecimal("150.00")));
            orderRepository.save(new Order("Maria Souza", "APPROVED", new BigDecimal("320.50")));
            orderRepository.save(new Order("Carlos Santos", "CANCELLED", new BigDecimal("75.25")));
            orderRepository.save(new Order("Ana Oliveira", "APPROVED", new BigDecimal("1250.00")));
            orderRepository.save(new Order("Pedro Lima", "PENDING", new BigDecimal("89.90")));
            orderRepository.save(new Order("Fernanda Costa", "APPROVED", new BigDecimal("450.75")));
            orderRepository.save(new Order("Ricardo Almeida", "PENDING", new BigDecimal("2100.00")));
            orderRepository.save(new Order("Juliana Pereira", "CANCELLED", new BigDecimal("33.50")));
            orderRepository.save(new Order("Bruno Ferreira", "APPROVED", new BigDecimal("780.00")));
            orderRepository.save(new Order("Camila Rodrigues", "PENDING", new BigDecimal("199.99")));

            System.out.println("10 pedidos de exemplo criados com sucesso!");
        }
    }
}