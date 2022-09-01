package com.geekbrains.cartservice.repository;


import com.geekbrains.cartservice.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {



}
