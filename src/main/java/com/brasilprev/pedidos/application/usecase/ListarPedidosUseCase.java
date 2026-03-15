package com.brasilprev.pedidos.application.usecase;

import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPedidosUseCase {

    private final PedidoRepository pedidoRepository;

    public ListarPedidosUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> executar() {
        return pedidoRepository.findAll();
    }
}
