package com.ecommerce.foodordering.repository;

import com.ecommerce.foodordering.entities.Order;
import com.ecommerce.foodordering.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findByUserIdAndStatus(Long userId, OrderStatus status);
    List<Order> findByUserIdAndStatusIn(Long userId, List<OrderStatus> status);

    List<Order> findAllByStatusIn(List<OrderStatus> orderStatusList);
}
