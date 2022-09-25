package com.geekbrains.orderservice.repository;


import com.geekbrains.orderservice.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {



}
