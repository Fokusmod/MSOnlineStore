package com.geekbrains.cartservice.service;

import com.geekbrains.apiservice.CartDto;
import com.geekbrains.apiservice.CartItemDto;
import com.geekbrains.apiservice.annotation.ExecutionTime;
import com.geekbrains.cartservice.exception.ResourceNotFoundException;
import com.geekbrains.cartservice.model.Cart;
import com.geekbrains.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final KafkaTemplate kafkaTemplate;

    @ExecutionTime
    @Cacheable(value = "cart", key = "#userId")
    public Cart getCart(Long userId) {
        Cart cart = cartRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        ListenableFuture<SendResult<String, CartDto>> future = kafkaTemplate.send("getCart", new CartDto(cart.getUserId(), cart.getItems()));
        future.addCallback(new ListenableFutureCallback<SendResult<String, CartDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.debug("{}", "Cart has not been added to topic");
            }

            @Override
            public void onSuccess(SendResult<String, CartDto> result) {
                log.debug("{}", "Successful adding cart for callService");
            }
        });
        return cart;
    }


    public Cart createCart(Long userId) {
        return cartRepository.save(new Cart(userId, new ArrayList<>()));
    }

    @ExecutionTime
    @CachePut(value = "cart", key = "#userId")
    public Cart addToCart(Long userId, CartItemDto cartItem) {
        if (cartRepository.findById(userId).isEmpty()) {
            createCart(userId);
        }
        Cart cart = getCart(userId);
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>(List.of(cartItem)));
            return cartRepository.save(cart);
        }

        List<CartItemDto> list = cart.getItems();
        for (CartItemDto item : list) {
            if (item.getTitle().equals(cartItem.getTitle())) {
                item.setCount(item.getCount() + cartItem.getCount());
                item.setSum(item.getSum() + cartItem.getSum());
                return cartRepository.save(cart);
            }
        }
        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    @ExecutionTime
    @CachePut(value = "cart", key = "#userId")
    public Cart deleteProduct(Long userId, CartItemDto cartItem) {
        Cart cart = getCart(userId);
        cart.getItems().remove(cartItem);
        return cartRepository.save(cart);
    }

    @CachePut(value = "cart", key = "#userId")
    public void deleteCart(Long userId) {
        Cart cart = getCart(userId);
        cartRepository.delete(cart);
    }

}
