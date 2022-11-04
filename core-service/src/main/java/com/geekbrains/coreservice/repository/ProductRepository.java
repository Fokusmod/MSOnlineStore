package com.geekbrains.coreservice.repository;



import com.geekbrains.coreservice.model.Category;
import com.geekbrains.coreservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p order by p.id asc")
    List<Product> findAll();

    @Query("select p from Product p where p.price <= :price")
    List<Product> findMin (int price);

    @Query("select p from Product p where p.price >= :price")
    List<Product> findMax (int price);

    List<Product> findByPriceBetween (int min, int max);

    Optional<Product> findByTitle(String title);

    Page<Product> findByCategory(Category category, Pageable pageable);
}
