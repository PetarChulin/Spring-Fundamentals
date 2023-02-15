package com.example.shopping_list.seeders;

import com.example.shopping_list.model.entity.Category;
import com.example.shopping_list.model.entity.CategoryEnum;
import com.example.shopping_list.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
            if (this.categoryRepository.count() == 0) {
                List<Category> categories = Arrays.stream(CategoryEnum.values())
                        .map(categoryEnum -> new Category(categoryEnum)).toList();

                this.categoryRepository.saveAll(categories);
            }
        }
}
