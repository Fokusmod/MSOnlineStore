package com.geekbrains.callservice.repository;

import com.geekbrains.callservice.entity.CallRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CallRequestRepository extends JpaRepository<CallRequest,Long> {

    Optional<CallRequest> findByUserId(Long id);

}
