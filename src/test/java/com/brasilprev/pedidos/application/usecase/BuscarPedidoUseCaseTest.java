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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarPedidoUseCaseTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private BuscarPedidoUseCase buscarPedidoUseCase;

    @Test
    @DisplayName("deve retornar pedido quando id existe")
    void deve_retornarPedido_quando_idExiste() {
        Pedido pedido = new Pedido("João Silva", "PENDENTE", new BigDecimal("150.00"));
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = buscarPedidoUseCase.executar(1L);

        assertThat(resultado.getClienteNome()).isEqualTo("João Silva");
        assertThat(resultado.getStatus()).isEqualTo("PENDENTE");
    }

    @Test
    @DisplayName("deve lançar exceção quando pedido não encontrado")
    void deve_lancarExcecao_quando_pedidoNaoEncontrado() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> buscarPedidoUseCase.executar(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Pedido não encontrado");
    }
}
