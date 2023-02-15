package com.example.battleship.seeders;

import com.example.battleship.models.entity.Category;
import com.example.battleship.models.entity.Type;
import com.example.battleship.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategorySeeder(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        if(this.categoryRepo.count() == 0) {
        List<Category> categories = Arrays.stream(Type.values())
                .map(Category::new).toList();

        this.categoryRepo.saveAll(categories);

        }
    }
}
