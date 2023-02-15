package com.example.coffee_shop.seeders;

import com.example.coffee_shop.model.entity.Category;
import com.example.coffee_shop.model.entity.CategoryEnum;
import com.example.coffee_shop.model.entity.Order;
import com.example.coffee_shop.repositories.CategoryRepository;
import com.example.coffee_shop.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
                        Category category = new Category();
                        category.setName(categoryEnum);
                        switch (categoryEnum) {
                            case DRINK -> category.setNeededTime(1);
                            case COFFEE -> category.setNeededTime(2);
                            case OTHER -> category.setNeededTime(5);
                            case CAKE -> category.setNeededTime(10);
                        }
                        this.categoryRepository.save(category);
                    });
        }
    }
}
