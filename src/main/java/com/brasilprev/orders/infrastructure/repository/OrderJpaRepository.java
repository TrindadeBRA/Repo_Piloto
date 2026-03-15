package com.brasilprev.orders.infrastructure.repository;

import com.brasilprev.orders.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Output adapter — JPA implementation of the OrderRepository port.
 */
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}