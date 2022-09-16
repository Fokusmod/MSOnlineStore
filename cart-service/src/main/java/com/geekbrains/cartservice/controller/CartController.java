package com.geekbrains.cartservice.controller;

import com.geekbrains.apiservice.*;
import com.geekbrains.cartservice.integrator.ProductServiceIntegration;
import com.geekbrains.cartservice.integrator.UserServiceIntegration;
import com.geekbrains.cartservice.model.Cart;
import com.geekbrains.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class CartController {

    private final CartService cartService;

    private final KafkaTemplate kafkaTemplate;
    private final UserServiceIntegration userServiceIntegration;
    private final ProductServiceIntegration productServiceIntegration;


    @GetMapping("/cart/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Cart showCart(@PathVariable String username) {
        Cart cart = cartService.getCart(userServiceIntegration.findByUsername(username).getId());

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

    @PostMapping("/cart")
    public void addProduct(@RequestBody UserAndProductInfo userInfo) {
        ProductDto product = productServiceIntegration.findById(userInfo.getProductId());

        CartItemDto cartItem = CartItemDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .count(1L)
                .sum(product.getPrice())
                .build();

        UserDto user = userServiceIntegration.findByUsername(userInfo.getUsername());
        cartService.addToCart(user.getId(), cartItem);
    }

    @PutMapping("/cart")
    public void deleteProduct(@RequestBody UserAndProductInfo userInfo) {
        ProductDto product = productServiceIntegration.findById(userInfo.getProductId());

        CartItemDto cartItem = CartItemDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .count(1L)
                .sum(product.getPrice())
                .build();
        UserDto user = userServiceIntegration.findByUsername(userInfo.getUsername());
        cartService.deleteProduct(user.getId(), cartItem);
    }


}
