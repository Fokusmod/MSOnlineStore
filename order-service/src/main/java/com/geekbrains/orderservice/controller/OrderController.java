package com.geekbrains.orderservice.controller;

import com.geekbrains.apiservice.OrderDto;
import com.geekbrains.orderservice.model.Order;
import com.geekbrains.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
    }

    @GetMapping("/order/{username}")
    public Order getOrder(@PathVariable String username) {
        return orderService.getOrder(username);
    }

    @GetMapping("/status/{username}")
    public void orderPayed(@PathVariable String username){
        orderService.orderStatusPayed(username);
    }

}
