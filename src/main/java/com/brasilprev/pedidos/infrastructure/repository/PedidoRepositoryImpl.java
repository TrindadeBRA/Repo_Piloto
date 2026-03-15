package com.brasilprev.pedidos.infrastructure.repository;

import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.domain.repository.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoJpaRepository jpaRepository;

    public PedidoRepositoryImpl(PedidoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
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
