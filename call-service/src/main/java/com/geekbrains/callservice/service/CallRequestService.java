package com.geekbrains.callservice.service;

import com.geekbrains.apiservice.CallRequestDto;
import com.geekbrains.apiservice.CartDto;
import com.geekbrains.apiservice.CartItemDto;
import com.geekbrains.callservice.entity.CallRequest;
import com.geekbrains.callservice.entity.CartItem;
import com.geekbrains.callservice.exception.CallRequestException;
import com.geekbrains.callservice.exception.MarketError;
import com.geekbrains.callservice.repository.CallRequestRepository;
import com.geekbrains.callservice.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CallRequestService {

    private final CallRequestRepository callRequestRepository;
    private final CartItemRepository cartItemRepository;


    @KafkaListener(topics = "getCart", groupId = "call_request")
    public void getTopicFromCartService(CartDto cartDto) {
        log.debug("Received message from getCart");
        saveRequest(cartDto);
    }

    private void saveRequest(CartDto cartDto) {
        CallRequest callRequest = new CallRequest();
        callRequest.setUserId(cartDto.getUserId());

        if (callRequestRepository.findByUserId(cartDto.getUserId()).isEmpty()) {
            callRequestRepository.save(callRequest);
        }

        CallRequest request = callRequestRepository.findByUserId(cartDto.getUserId())
                .orElseThrow(() -> new CallRequestException(List.of("Call request not found")));

        cartItemRepository.deleteByCallRequest(request);
        List<CartItemDto> cartItems = cartDto.getItems();

        for (CartItemDto cartItem : cartItems) {
            CartItem item = new CartItem();
            item.setCallRequest(request);
            item.setPrice(cartItem.getPrice());
            item.setCount(cartItem.getCount());
            item.setTitle(cartItem.getTitle());
            item.setSum(cartItem.getSum());
            cartItemRepository.save(item);
        }
    }

    public List<CallRequestDto> getAllRequest() {
        return callRequestRepository.findAll().stream()
                .map(callRequest ->
                        new CallRequestDto(
                                callRequest.getId(),
                                callRequest.getUserId(),
                                callRequest.getList()
                                .stream()
                                .map(cartItem -> new CartItemDto(
                                        cartItem.getId(),
                                        cartItem.getTitle(),
                                        cartItem.getCount(),
                                        cartItem.getPrice(),
                                        cartItem.getSum()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public CallRequestDto getById(Long id) {
        return callRequestRepository.findByUserId(id)
                .stream()
                .map(callRequest -> new CallRequestDto(
                        callRequest.getId(),
                        callRequest.getUserId(),
                        callRequest.getList()
                        .stream()
                        .map(cartItem -> new CartItemDto(
                                cartItem.getId(),
                                cartItem.getTitle(),
                                cartItem.getCount(),
                                cartItem.getPrice(),
                                cartItem.getSum()))
                        .collect(Collectors.toList()))).findFirst()
                .orElseThrow(() -> new CallRequestException(List.of("Call request not found")));
    }

    public void deleteRequest(Long id) {
        CallRequest callRequest = callRequestRepository.findByUserId(id)
                .orElseThrow(() -> new CallRequestException(List.of("Call request not found")));
        cartItemRepository.deleteByCallRequest(callRequest);
        callRequestRepository.deleteById(id);
    }

}
