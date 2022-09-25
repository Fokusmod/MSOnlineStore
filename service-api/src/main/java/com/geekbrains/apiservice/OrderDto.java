package com.geekbrains.apiservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String address;

    private String username;

    private String phoneNumber;

    private List<CartItemDto> list;

    private Long price;

}
