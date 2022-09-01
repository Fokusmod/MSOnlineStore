package com.geekbrains.coreservice.converter;

import com.geekbrains.apiservice.CategoryDto;
import com.geekbrains.coreservice.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {


    public CategoryDto entityToDto(Category category) {
        return new CategoryDto(category.getId(), category.getTitle());
    }

}
