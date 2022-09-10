package com.geekbrains.api;

import com.geekbrains.model.Category;
import com.geekbrains.model.Error;
import com.geekbrains.model.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-04T04:25:54.001546200+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.swaggerOnlineMarketOpenAPI30.base-path:/api/v1}")
public class ProductsApiController implements ProductsApi {

    private final ProductsApiDelegate delegate;

    public ProductsApiController(@Autowired(required = false) ProductsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ProductsApiDelegate() {});
    }

    @Override
    public ProductsApiDelegate getDelegate() {
        return delegate;
    }

    @Override
    public ResponseEntity<List<Product>> findAllProducts(Integer page, Integer pageSize) {
        //Заглушка
        Product product = new Product();
        product.setId(1L);
        product.setTitle("MockProduct");
        product.setPrice(200);
        product.categoryTitle(new Category(1L,"Mock"));
        List<Product> list = new ArrayList<>();
        list.add(product);
        return ResponseEntity.ok(list);
    }
}
