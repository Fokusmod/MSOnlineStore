package com.geekbrains.orderservice.repository;

import com.geekbrains.orderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
