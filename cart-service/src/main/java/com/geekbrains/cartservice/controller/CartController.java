package com.geekbrains.cartservice.controller;

import com.geekbrains.apiservice.CartDto;
import com.geekbrains.apiservice.ProductDto;
import com.geekbrains.apiservice.UserDto;
import com.geekbrains.cartservice.integrator.ProductServiceIntegration;
import com.geekbrains.cartservice.integrator.UserServiceIntegration;
import com.geekbrains.cartservice.model.Cart;
import com.geekbrains.cartservice.model.CartItem;
import com.geekbrains.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

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
        return cartService.getCart(userServiceIntegration.findByUsername(username).getId());

    }

    @PostMapping("/cart")
    public void addProduct(@RequestBody CartDto cartDto) {
        ProductDto product = productServiceIntegration.findById(cartDto.getProductId());

        CartItem cartItem = CartItem.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .count(1L)
                .sum(product.getPrice())
                .build();

        UserDto user = userServiceIntegration.findByUsername(cartDto.getUsername());
        cartService.addToCart(user.getId(), cartItem);
    }

    @PutMapping("/cart")
    public void deleteProduct(@RequestBody CartDto cart) {
        ProductDto product = productServiceIntegration.findById(cart.getProductId());

        CartItem cartItem = CartItem.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .count(1L)
                .sum(product.getPrice())
                .build();
        UserDto user = userServiceIntegration.findByUsername(cart.getUsername());
        cartService.deleteProduct(user.getId(), cartItem);
    }


}
