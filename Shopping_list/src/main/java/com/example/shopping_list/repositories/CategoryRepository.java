package com.example.shopping_list.repositories;

import com.example.shopping_list.model.entity.Category;
import com.example.shopping_list.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByName(CategoryEnum name);
}
