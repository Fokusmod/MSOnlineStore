package com.geekbrains.apiservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto implements Serializable {

    private Long id;

    private String title;

    private Long count;

    private int price;

    private int sum;

}
