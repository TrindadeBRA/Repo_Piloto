package com.brasilprev.pedidos.application.usecase;

import com.brasilprev.pedidos.domain.model.Pedido;
import com.brasilprev.pedidos.domain.repository.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
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
    @DisplayName("deve retornar página de pedidos quando repositório tem dados")
    void deve_retornarPaginaDePedidos_quando_repositorioTemDados() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Pedido> pedidos = List.of(
                new Pedido("João Silva", "PENDENTE", new BigDecimal("150.00")),
                new Pedido("Maria Souza", "APROVADO", new BigDecimal("320.50"))
        );
        Page<Pedido> paginaEsperada = new PageImpl<>(pedidos, pageable, pedidos.size());

        when(pedidoRepository.findAll(pageable)).thenReturn(paginaEsperada);

        Page<Pedido> resultado = listarPedidosUseCase.executar(pageable);

        assertThat(resultado.getContent()).hasSize(2);
        assertThat(resultado.getTotalElements()).isEqualTo(2);
    }

    @Test
    @DisplayName("deve retornar página vazia quando repositório não tem dados")
    void deve_retornarPaginaVazia_quando_repositorioNaoTemDados() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Pedido> paginaVazia = Page.empty(pageable);

        when(pedidoRepository.findAll(pageable)).thenReturn(paginaVazia);

        Page<Pedido> resultado = listarPedidosUseCase.executar(pageable);

        assertThat(resultado.getContent()).isEmpty();
    }
}
