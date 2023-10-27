package com.eom.shoppingmall.shoppingmall.repository;

import com.eom.shoppingmall.shoppingmall.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
