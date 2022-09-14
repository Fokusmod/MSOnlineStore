package com.geekbrains.callservice.service;

import com.geekbrains.callservice.entity.Statistic;
import com.geekbrains.callservice.repository.StatisticRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CallStatisticService {

    private final StatisticRepository statisticRepository;

    public void saveStatistic(String className, String method, Long time) {

        if (statisticRepository.findByClassName(className).isEmpty()) {
            Statistic statistic = new Statistic(className, method, new ArrayList<>(List.of(time)));
            statisticRepository.save(statistic);
        } else {
            List<Statistic> statistic = statisticRepository.findByClassName(className);
            for (Statistic stat : statistic) {
                if (stat.getMethod().equals(method)) {
                    if (stat.getTime().size() != stat.getSize()) {
                        stat.getTime().add(time);
                        statisticRepository.save(stat);
                    } else {
                        stat.getTime().remove(9);
                        stat.getTime().add(0,time);
                        statisticRepository.save(stat);
                    }
                    return;
                }
            }
            Statistic newStatistic = new Statistic(className, method, new ArrayList<>(List.of(time)));
            statisticRepository.save(newStatistic);
        }
    }
}
