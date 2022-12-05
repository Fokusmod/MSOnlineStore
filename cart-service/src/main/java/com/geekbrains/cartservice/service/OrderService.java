package com.geekbrains.cartservice.service;

import com.geekbrains.apiservice.CartItemDto;
import com.geekbrains.apiservice.OrderDto;
import com.geekbrains.cartservice.exception.ResourceNotFoundException;
import com.geekbrains.cartservice.model.Order;
import com.geekbrains.cartservice.model.OrderItem;
import com.geekbrains.cartservice.model.Status;
import com.geekbrains.cartservice.repository.OrderItemRepository;
import com.geekbrains.cartservice.repository.OrderRepository;
import com.geekbrains.cartservice.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemRepository orderItemRepository;

    private final OrderRepository orderRepository;

    private final StatusRepository statusRepository;


    public Order getOrder(String username) {
        return orderRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Order not fount"));
    }

    @Transactional
    public void saveOrder(OrderDto orderDto) {
        Status status = statusRepository.findById(2L).get();
        List<CartItemDto> list = orderDto.getList();

        if (orderRepository.findByUsername(orderDto.getUsername()).isEmpty()) {
            Order prepareOrder = new Order();
            prepareOrder.setAddress(orderDto.getAddress());
            prepareOrder.setUsername(orderDto.getUsername());
            prepareOrder.setPhoneNumber(orderDto.getPhoneNumber());
            prepareOrder.setStatus(status);
            orderRepository.save(prepareOrder);
        }

        Order order = orderRepository.findByUsername(orderDto.getUsername()).get();
        if (!order.getAddress().equals(orderDto.getAddress())) {
            order.setAddress(orderDto.getAddress());
        }

        if (!order.getPhoneNumber().equals(orderDto.getPhoneNumber())) {
            order.setPhoneNumber(orderDto.getPhoneNumber());
        }

        List<OrderItem> orderItems = order.getOrderItems();

        List<OrderItem> prepareItems = list.stream()
                .map(cartItemDto -> new OrderItem(
                        order.getId(),
                        cartItemDto.getTitle(),
                        cartItemDto.getCount().intValue(),
                        (double) cartItemDto.getPrice(),
                        (double) cartItemDto.getSum())).collect(Collectors.toList());
        if (!(orderItems == null)) {
            for (OrderItem orderItem : orderItems) {
                for (OrderItem prepareItem : prepareItems) {
                    if (orderItem.getOrder_id().equals(prepareItem.getOrder_id())) {
                        orderItemRepository.delete(orderItem);
                    }
                }
            }
        }
        order.setOrderItems(prepareItems);
        orderRepository.saveAndFlush(order);
    }

    public void orderStatusPayed(String username) {
        Order order = getOrder(username);
        Status status = statusRepository.findById(1L).get();
        order.setStatus(status);
        orderRepository.save(order);
    }

    public void deleteOrder(String username) {
        Order order = getOrder(username);
        orderRepository.delete(order);
    }

}
