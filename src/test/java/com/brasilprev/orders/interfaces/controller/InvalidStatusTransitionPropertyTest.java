package com.brasilprev.orders.interfaces.controller;

// Feature: order-status-flow, Property 2: Resposta de erro para transição inválida
// **Validates: Requirements 3.1, 3.2**

import com.brasilprev.orders.application.usecase.TransitionOrderStatusUseCase;
import com.brasilprev.orders.domain.exception.InvalidStatusTransitionException;
import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.model.OrderStatus;
import com.brasilprev.orders.domain.repository.OrderRepository;
import com.brasilprev.orders.interfaces.dto.ApiResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvalidStatusTransitionPropertyTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private TransitionOrderStatusUseCase transitionOrderStatusUseCase;

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    /**
     * Gera TODOS os pares (from, to) onde canTransitionTo retorna false.
     * Para um enum com 4 valores, temos 16 pares totais menos 3 válidos = 13 pares inválidos.
     */
    static Stream<Arguments> invalidTransitionPairs() {
        List<Arguments> pairs = new ArrayList<>();
        for (OrderStatus from : OrderStatus.values()) {
            for (OrderStatus to : OrderStatus.values()) {
                if (!from.canTransitionTo(to)) {
                    pairs.add(Arguments.of(from, to));
                }
            }
        }
        return pairs.stream();
    }

    @ParameterizedTest(name = "deve_lancarExcecao_quando_transicaoInvalida_{0}_para_{1}")
    @MethodSource("invalidTransitionPairs")
    void deve_lancarInvalidStatusTransitionException_quando_transicaoInvalida(
            OrderStatus fromStatus, OrderStatus toStatus) {
        Order order = new Order("Cliente Teste", fromStatus, BigDecimal.valueOf(100.00));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> transitionOrderStatusUseCase.execute(1L, toStatus))
                .isInstanceOf(InvalidStatusTransitionException.class)
                .hasMessageContaining(fromStatus.name())
                .hasMessageContaining(toStatus.name());
    }

    @ParameterizedTest(name = "deve_retornarHttp422ComMensagem_quando_transicaoInvalida_{0}_para_{1}")
    @MethodSource("invalidTransitionPairs")
    void deve_retornarHttp422ComStatusNaMensagem_quando_transicaoInvalida(
            OrderStatus fromStatus, OrderStatus toStatus) {
        InvalidStatusTransitionException exception =
                new InvalidStatusTransitionException(fromStatus.name(), toStatus.name());

        ResponseEntity<ApiResponse<Object>> response =
                exceptionHandler.handleInvalidStatusTransition(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(422);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).contains(fromStatus.name());
        assertThat(response.getBody().getMessage()).contains(toStatus.name());
    }
}
