package com.brasilprev.pedidos.infrastructure.repository;

import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.domain.repository.PedidoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoJpaRepository jpaRepository;

    public PedidoRepositoryImpl(PedidoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Pedido> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return jpaRepository.save(pedido);
    }
}
