package com.geekbrains.apiservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAndProductInfo {

    private Long productId;
    private String username;
    private Long productCount;



}
