package com.brasilprev.pedidos.interfaces.controller;

import com.brasilprev.pedidos.application.usecase.BuscarPedidoUseCase;
import com.brasilprev.pedidos.application.usecase.ListarPedidosUseCase;
import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.interfaces.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final ListarPedidosUseCase listarPedidosUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;

    public PedidoController(ListarPedidosUseCase listarPedidosUseCase,
                            BuscarPedidoUseCase buscarPedidoUseCase) {
        this.listarPedidosUseCase = listarPedidosUseCase;
        this.buscarPedidoUseCase = buscarPedidoUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Pedido>>> listar() {
        List<Pedido> pedidos = listarPedidosUseCase.executar();
        return ResponseEntity.ok(ApiResponse.ok(pedidos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Pedido>> buscar(@PathVariable Long id) {
        Pedido pedido = buscarPedidoUseCase.executar(id);
        return ResponseEntity.ok(ApiResponse.ok(pedido));
    }
}
