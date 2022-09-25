package com.geekbrains.cartservice.model;

import com.geekbrains.apiservice.CartItemDto;
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

    private static final long serialVersionUID = 2291024608817676490L;

    @Id
    private Long userId;

    private List<CartItemDto> items;
    
}
