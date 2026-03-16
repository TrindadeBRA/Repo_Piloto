package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.model.OrderStatus;
import com.brasilprev.orders.domain.repository.OrderRepository;
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
class FindOrderUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private FindOrderUseCase findOrderUseCase;

    @Test
    @DisplayName("should return order when id exists")
    void should_returnOrder_when_idExists() {
        Order order = new Order("John Silva", OrderStatus.PENDENTE, new BigDecimal("150.00"));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = findOrderUseCase.execute(1L);

        assertThat(result.getCustomerName()).isEqualTo("John Silva");
        assertThat(result.getStatus()).isEqualTo(OrderStatus.PENDENTE);
    }

    @Test
    @DisplayName("should throw exception when order not found")
    void should_throwException_when_orderNotFound() {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findOrderUseCase.execute(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Order not found");
    }
}