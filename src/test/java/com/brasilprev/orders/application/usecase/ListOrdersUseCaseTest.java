package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.repository.OrderRepository;
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
class ListOrdersUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ListOrdersUseCase listOrdersUseCase;

    @Test
    @DisplayName("should return list of orders when repository has data")
    void should_returnListOfOrders_when_repositoryHasData() {
        List<Order> orders = List.of(
                new Order("John Silva", "PENDING", new BigDecimal("150.00")),
                new Order("Mary Souza", "APPROVED", new BigDecimal("320.50"))
        );

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = listOrdersUseCase.execute();

        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("should return empty list when repository has no data")
    void should_returnEmptyList_when_repositoryHasNoData() {
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());

        List<Order> result = listOrdersUseCase.execute();

        assertThat(result).isEmpty();
    }
}