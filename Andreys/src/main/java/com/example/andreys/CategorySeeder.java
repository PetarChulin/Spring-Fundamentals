package com.example.andreys;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.CategoryEnum;
import com.example.andreys.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {


    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryEnum.values())
                    .forEach(categoryEnum -> {
                        this.categoryRepository.save(new Category(categoryEnum, String.format("Description for %s"
                                , categoryEnum.name())));
                    });
        }
    }
}
