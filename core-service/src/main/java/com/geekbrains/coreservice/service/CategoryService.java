package com.geekbrains.coreservice.service;

import com.geekbrains.apiservice.annotation.ExecutionTime;
import com.geekbrains.coreservice.model.Category;
import com.geekbrains.coreservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @ExecutionTime
    public List<Category> findAll() {
       return categoryRepository.findAll();
    }
    @ExecutionTime
    public Optional<Category> findByTitle (String title) {
        return categoryRepository.findByTitle(title);
    }
}
