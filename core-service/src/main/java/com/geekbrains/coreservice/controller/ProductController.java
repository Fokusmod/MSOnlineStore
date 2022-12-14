package com.geekbrains.coreservice.controller;

import com.geekbrains.coreservice.Dto.ProductDto;
import com.geekbrains.coreservice.exception.DataValidationException;
import com.geekbrains.coreservice.exception.ResourceNotFoundException;
import com.geekbrains.coreservice.model.Product;
import com.geekbrains.coreservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> findAll(@RequestParam(name = "i", defaultValue = "1") int pageIndex) {
        return productService.findCatalog(pageIndex - 1, 12).map(ProductDto::new);
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return new ProductDto(product);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated ProductDto new_product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        productService.saveProductFromDto(new_product);
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void change(@RequestBody @Validated ProductDto change_product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        productService.updateProductFromDto(change_product);

    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("products/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findAll() {
        List<ProductDto> collect = productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/product/sort/{category}")
    public Page<ProductDto> findByCategory(@PathVariable String category, @RequestParam(name = "i", defaultValue = "1") int pageIndex) {
        return productService.findByCategory(category, pageIndex - 1, 12).map(ProductDto::new);
    }
}

