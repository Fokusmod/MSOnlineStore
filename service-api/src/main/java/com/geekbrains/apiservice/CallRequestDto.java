package com.geekbrains.apiservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallRequestDto {

    private Long id;

    private Long userId;

    private List<CartItemDto> cartItemDtoList;

}
