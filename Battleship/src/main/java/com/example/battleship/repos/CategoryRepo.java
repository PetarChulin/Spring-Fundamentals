package com.example.battleship.repos;

import com.example.battleship.models.entity.Category;
import com.example.battleship.models.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category findByName(Type name);
}
