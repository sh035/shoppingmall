package com.eom.shoppingmall.shoppingmall.repository;

import com.eom.shoppingmall.shoppingmall.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
