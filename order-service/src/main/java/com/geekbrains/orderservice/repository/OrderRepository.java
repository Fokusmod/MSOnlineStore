package com.geekbrains.orderservice.repository;

import com.geekbrains.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {


    Optional<Order> findByUsername(String username);
}
