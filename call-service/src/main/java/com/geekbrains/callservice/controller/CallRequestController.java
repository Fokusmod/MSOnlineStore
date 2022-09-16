package com.geekbrains.callservice.controller;


import com.geekbrains.apiservice.CallRequestDto;
import com.geekbrains.apiservice.CartItemDto;
import com.geekbrains.callservice.CallServiceApplication;
import com.geekbrains.callservice.entity.CallRequest;
import com.geekbrains.callservice.entity.Statistic;
import com.geekbrains.callservice.service.CallRequestService;
import com.geekbrains.callservice.service.CallStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/requests")
public class CallRequestController {

    private final CallRequestService callRequestService;

    private final CallStatisticService callStatisticService;


    @GetMapping()
    public List<CallRequestDto> getAllRequests() {
        return callRequestService.getAllRequest();

    }

    @GetMapping("/{id}")
    public CallRequestDto getByUserId(@PathVariable Long id){
        return callRequestService.getById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        callRequestService.deleteRequest(id);
    }

}
