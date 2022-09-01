package com.geekbrains.cartservice.service;

import com.geekbrains.cartservice.model.Cart;
import com.geekbrains.cartservice.model.CartItem;
import com.geekbrains.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;



    @Cacheable(value = "cart", key = "#userId")
    public Cart getCart(Long userId) {
        Cart cart = cartRepository.findById(userId).orElse(new Cart(userId, new ArrayList<>()));
        System.out.println(cart);
        return cart;
    }


    @CachePut(value = "cart", key = "#userId")
    public Cart addToCart(Long userId, CartItem cartItem) {
        Cart cart = getCart(userId);
        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    @CachePut(value = "cart", key = "#userId")
    public Cart deleteProduct(Long userId, CartItem cartItem) {
        Cart cart = getCart(userId);
        cart.getItems().remove(cartItem);
        return cartRepository.save(cart);
    }

}
