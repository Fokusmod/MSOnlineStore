package com.geekbrains.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("cart")
public class Cart implements Serializable{

    @Id
    private Long userId;

    private List<CartItem> items;

}
