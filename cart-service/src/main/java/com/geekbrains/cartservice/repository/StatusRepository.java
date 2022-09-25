package com.geekbrains.cartservice.repository;

import com.geekbrains.cartservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
