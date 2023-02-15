package com.example.shopping_list.repositories;

import com.example.shopping_list.model.entity.CategoryEnum;
import com.example.shopping_list.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_Name(CategoryEnum name);

    List<Product> findAll();

}
