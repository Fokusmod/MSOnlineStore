package com.geekbrains.callservice.repository;

import com.geekbrains.callservice.entity.CallRequest;
import com.geekbrains.callservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    void deleteByCallRequest(CallRequest callRequest);
}
