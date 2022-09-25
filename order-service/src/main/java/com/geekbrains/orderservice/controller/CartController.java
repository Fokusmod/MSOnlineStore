package com.geekbrains.orderservice.controller;

import com.geekbrains.apiservice.*;
import com.geekbrains.orderservice.integrator.ProductServiceIntegration;
import com.geekbrains.orderservice.integrator.UserServiceIntegration;
import com.geekbrains.orderservice.model.Cart;
import com.geekbrains.orderservice.service.CartService;
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
    private final UserServiceIntegration userServiceIntegration;
    private final ProductServiceIntegration productServiceIntegration;


    @GetMapping("/cart/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Cart showCart(@PathVariable String username) {
        Long id = userServiceIntegration.findByUsername(username).getId();
        return cartService.getCart(id);
    }

    @PostMapping("/cart")
    public void addProduct(@RequestBody UserAndProductInfo userInfo) {
        ProductDto product = productServiceIntegration.findById(userInfo.getProductId());

        CartItemDto cartItem = CartItemDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .count(userInfo.getProductCount())
                .sum((int) (product.getPrice() * userInfo.getProductCount()))
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
                .count(userInfo.getProductCount())
                .sum((int) (product.getPrice() * userInfo.getProductCount()))
                .build();
        UserDto user = userServiceIntegration.findByUsername(userInfo.getUsername());
        cartService.deleteProduct(user.getId(), cartItem);
    }

    @DeleteMapping("/cart/{username}")
    public void deleteCart(@PathVariable String username) {
       cartService.deleteCart(userServiceIntegration.findByUsername(username).getId());
    }


}
