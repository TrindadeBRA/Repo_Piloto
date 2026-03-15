package com.brasilprev.pedidos.interfaces.controller;

import com.brasilprev.pedidos.application.usecase.ListarPedidosUseCase;
import com.brasilprev.pedidos.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final ListarPedidosUseCase listarPedidosUseCase;

    public PedidoController(ListarPedidosUseCase listarPedidosUseCase) {
        this.listarPedidosUseCase = listarPedidosUseCase;
    }

    @GetMapping
    public Page<Pedido> listar(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        return listarPedidosUseCase.executar(PageRequest.of(page, size));
    }
}
