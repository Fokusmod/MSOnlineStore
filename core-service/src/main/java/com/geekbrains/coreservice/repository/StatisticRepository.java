package com.geekbrains.coreservice.repository;

import com.geekbrains.coreservice.model.Statistic;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableRedisRepositories
public interface StatisticRepository extends CrudRepository<Statistic,String> {

   List<Statistic> findByClassName(String className);
}
