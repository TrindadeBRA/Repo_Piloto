package com.brasilprev.pedidos.application.usecase;

import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public BuscarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido executar(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
    }
}
