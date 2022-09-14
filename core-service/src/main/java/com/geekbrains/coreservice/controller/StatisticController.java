package com.geekbrains.coreservice.controller;

import com.geekbrains.coreservice.model.Statistic;
import com.geekbrains.coreservice.repository.StatisticRepository;
import com.geekbrains.coreservice.service.CoreStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/stats")
public class StatisticController {

    private final CoreStatisticService coreStatisticService;

    @GetMapping("/all")
    public List<Statistic> getAllStatistic(){
        return coreStatisticService.getAllStatistic();
    }

}
