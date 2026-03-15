package com.brasilprev.pedidos.domain.repository;

import com.brasilprev.pedidos.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Porta de saída — interface do domínio para persistência.
 * A implementação fica em infrastructure/.
 */
public interface PedidoRepository {

    Page<Pedido> findAll(Pageable pageable);

    Optional<Pedido> findById(Long id);

    Pedido save(Pedido pedido);
}
