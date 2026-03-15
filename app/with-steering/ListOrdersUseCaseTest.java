package com.brasilprev.orders.application.usecase;

import com.brasilprev.orders.domain.model.Order;
import com.brasilprev.orders.domain.repository.OrderRepository;
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
class ListOrdersUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ListOrdersUseCase listOrdersUseCase;

    @Test
    @DisplayName("should return page of orders when repository has data")
    void should_returnPageOfOrders_when_repositoryHasData() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Order> orders = List.of(
                new Order("John Silva", "PENDING", new BigDecimal("150.00")),
                new Order("Mary Souza", "APPROVED", new BigDecimal("320.50"))
        );
        Page<Order> expectedPage = new PageImpl<>(orders, pageable, orders.size());

        when(orderRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Order> result = listOrdersUseCase.execute(pageable);

        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getTotalElements()).isEqualTo(2);
    }

    @Test
    @DisplayName("should return empty page when repository has no data")
    void should_returnEmptyPage_when_repositoryHasNoData() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> emptyPage = Page.empty(pageable);

        when(orderRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<Order> result = listOrdersUseCase.execute(pageable);

        assertThat(result.getContent()).isEmpty();
    }
}