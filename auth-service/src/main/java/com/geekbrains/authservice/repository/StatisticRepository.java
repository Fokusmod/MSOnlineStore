package com.geekbrains.authservice.repository;

import com.geekbrains.authservice.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableRedisRepositories
public interface StatisticRepository extends CrudRepository<Statistic,String> {

   List<Statistic> findByClassName(String className);
}
