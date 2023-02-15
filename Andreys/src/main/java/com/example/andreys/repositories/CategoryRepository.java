package com.example.andreys.repositories;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryEnum name);

}
