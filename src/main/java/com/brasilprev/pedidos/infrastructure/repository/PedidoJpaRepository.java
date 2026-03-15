package com.brasilprev.pedidos.infrastructure.repository;

import com.brasilprev.pedidos.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Adaptador de saída — implementação JPA da porta PedidoRepository.
 */
public interface PedidoJpaRepository extends JpaRepository<Pedido, Long> {
}
