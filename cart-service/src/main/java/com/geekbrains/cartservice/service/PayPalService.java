package com.geekbrains.cartservice.service;

import com.geekbrains.cartservice.model.Order;

import com.geekbrains.cartservice.model.OrderItem;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayPalService {

    private final OrderService orderService;

    @Transactional
    public OrderRequest createOrderRequest(String username) {
        Order order = orderService.getOrder(username);
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("Spring Web Market")
                .landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId(order.getId().toString())
                .description("Spring Web Market Order")
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("RUB").value(String.valueOf(getTotalPrice(order)))
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("RUB").value(String.valueOf(getTotalPrice(order))))))
                .items(order.getOrderItems().stream()
                        .map(orderItem -> new Item()
                                .name(orderItem.getTitle())
                                .unitAmount(new Money().currencyCode("RUB").value(String.valueOf(orderItem.getPrice())))
                                .quantity(String.valueOf(orderItem.getCount()))
                                .sku(String.valueOf(orderItem.getId())))
                        .collect(Collectors.toList()))
                .shippingDetail(new ShippingDetail().name(new Name().fullName(order.getUsername()))
                        .addressPortable(new AddressPortable()
                                .addressLine1(order.getAddress())
                                .addressLine2("Floor 6")
                                .adminArea2("San Francisco")
                                .adminArea1("CA")
                                .postalCode("94107")
                                .countryCode("US"))
                );
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

    public int getTotalPrice(Order order) {
        int totalPrice = 0;
        List<OrderItem> list = order.getOrderItems();
        for (OrderItem orderItem : list) {
            totalPrice += orderItem.getSum();
        }
        return totalPrice;
    }
}
