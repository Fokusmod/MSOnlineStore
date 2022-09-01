package com.geekbrains.coreservice.converter;

import com.geekbrains.apiservice.ProductDto;
import com.geekbrains.coreservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {


    public ProductDto entityToDto (Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

}
