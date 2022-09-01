package com.geekbrains.coreservice.controller;

import com.geekbrains.apiservice.CategoryDto;
import com.geekbrains.coreservice.converter.CategoryConverter;
import com.geekbrains.coreservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryConverter categoryConverter;

    @GetMapping("/category")
    public List<CategoryDto> findAll (){
       return categoryService.findAll().stream().map(categoryConverter::entityToDto).collect(Collectors.toList());
    }
}
