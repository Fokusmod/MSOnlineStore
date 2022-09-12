package com.geekbrains.cartservice.service;

import com.geekbrains.apiservice.CartDto;
import com.geekbrains.apiservice.CartItemDto;
import com.geekbrains.cartservice.model.Cart;
import com.geekbrains.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    private KafkaTemplate<String, CartDto> kafkaTemplate;


    @Cacheable(value = "cart", key = "#userId")
    public Cart getCart(Long userId) {
        Cart cart = cartRepository.findById(userId).orElse(new Cart(userId, new ArrayList<>()));
        return cart;
    }


    @CachePut(value = "cart", key = "#userId")
    public Cart addToCart(Long userId, CartItemDto cartItem) {
        Cart cart = getCart(userId);
        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    @CachePut(value = "cart", key = "#userId")
    public Cart deleteProduct(Long userId, CartItemDto cartItem) {
        Cart cart = getCart(userId);
        cart.getItems().remove(cartItem);
        return cartRepository.save(cart);
    }

}
