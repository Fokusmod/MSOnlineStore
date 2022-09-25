package com.geekbrains.cartservice.repository;

import com.geekbrains.cartservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
