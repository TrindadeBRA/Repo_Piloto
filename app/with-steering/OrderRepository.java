package com.brasilprev.orders.domain.repository;

import com.brasilprev.orders.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Output port — domain interface for persistence.
 * Implementation is in infrastructure/.
 */
public interface OrderRepository {

    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(Long id);

    Order save(Order order);

    void deleteById(Long id);
}