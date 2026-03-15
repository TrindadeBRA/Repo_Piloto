package com.brasilprev.pedidos.application.usecase;

import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.domain.repository.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarPedidosUseCase {

    private final PedidoRepository pedidoRepository;

    public ListarPedidosUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Page<Pedido> executar(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }
}
