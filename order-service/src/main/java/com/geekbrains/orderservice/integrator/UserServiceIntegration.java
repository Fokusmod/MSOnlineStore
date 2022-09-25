package com.geekbrains.orderservice.integrator;

import com.geekbrains.apiservice.UserDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class UserServiceIntegration {

    private final WebClient userServiceWebClient;

    public UserDto findByUsername(String username) {
        return userServiceWebClient.get()
                .uri("api/v1/user/" + username)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

//    public  findById(Long id) {
//        return webClient.get()
//                .uri("/api/v1/products/" + id)
//                .retrieve()
//                .bodyToMono(ProductDto.class)
//                .block();
//    }
}
