package com.geekbrains.orderservice.repository;

import com.geekbrains.orderservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
