package com.geekbrains.coreservice.service;

import com.geekbrains.coreservice.model.Statistic;
import com.geekbrains.coreservice.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoreStatisticService {

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

    public List<Statistic> getAllStatistic() {
        List<Statistic> statistics = new ArrayList<>();
        statisticRepository.findAll().forEach(statistics::add);
        return statistics;
    }


}
