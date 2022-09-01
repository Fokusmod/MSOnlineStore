package com.geekbrains.coreservice.repository;


import com.geekbrains.coreservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByTitle(String title);
}
