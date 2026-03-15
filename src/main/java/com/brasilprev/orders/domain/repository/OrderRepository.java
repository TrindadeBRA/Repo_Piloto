package com.brasilprev.orders.domain.repository;

import com.brasilprev.orders.domain.model.Order;
import java.util.List;
import java.util.Optional;

/**
 * Output port — domain interface for persistence.
 * Implementation is in infrastructure/.
 */
public interface OrderRepository {

    List<Order> findAll();

    Optional<Order> findById(Long id);

    Order save(Order order);
    
    void deleteById(Long id);
}