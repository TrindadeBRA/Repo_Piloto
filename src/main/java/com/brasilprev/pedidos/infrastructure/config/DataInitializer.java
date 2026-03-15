package com.brasilprev.pedidos.infrastructure.config;

import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.infrastructure.repository.PedidoJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Popula o banco H2 em memória com dados de exemplo para o workshop.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final PedidoJpaRepository repository;

    public DataInitializer(PedidoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.save(new Pedido("João Silva", "PENDENTE", new BigDecimal("150.00")));
        repository.save(new Pedido("Maria Souza", "APROVADO", new BigDecimal("320.50")));
        repository.save(new Pedido("Carlos Lima", "CANCELADO", new BigDecimal("89.90")));
        repository.save(new Pedido("Ana Costa", "PENDENTE", new BigDecimal("540.00")));
        repository.save(new Pedido("Pedro Alves", "APROVADO", new BigDecimal("210.75")));
    }
}
