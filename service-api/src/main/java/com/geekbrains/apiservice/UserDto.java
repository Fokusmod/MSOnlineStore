package com.geekbrains.apiservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String Email;


    public UserDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
