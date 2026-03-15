package com.brasilprev.pedidos.application.usecase;

import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.domain.repository.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarPedidosUseCaseTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ListarPedidosUseCase listarPedidosUseCase;

    @Test
    @DisplayName("deve retornar lista de pedidos quando repositório tem dados")
    void deve_retornarListaDePedidos_quando_repositorioTemDados() {
        List<Pedido> pedidos = List.of(
                new Pedido("João Silva", "PENDENTE", new BigDecimal("150.00")),
                new Pedido("Maria Souza", "APROVADO", new BigDecimal("320.50"))
        );

        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<Pedido> resultado = listarPedidosUseCase.executar();

        assertThat(resultado).hasSize(2);
    }

    @Test
    @DisplayName("deve retornar lista vazia quando repositório não tem dados")
    void deve_retornarListaVazia_quando_repositorioNaoTemDados() {
        when(pedidoRepository.findAll()).thenReturn(Collections.emptyList());

        List<Pedido> resultado = listarPedidosUseCase.executar();

        assertThat(resultado).isEmpty();
    }
}
