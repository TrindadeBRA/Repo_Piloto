package com.brasilprev.pedidos.domain.repository;

import com.brasilprev.pedidos.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

/**
 * Porta de saída — interface do domínio para persistência.
 * A implementação fica em infrastructure/.
 */
public interface PedidoRepository {

    List<Pedido> findAll();

    Optional<Pedido> findById(Long id);

    Pedido save(Pedido pedido);
}
