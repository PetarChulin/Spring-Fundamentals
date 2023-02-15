package com.example.gira.seeders;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.ClassificationNameEnum;
import com.example.gira.repositories.ClassificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ClassificationSeeder implements CommandLineRunner {

    private final ClassificationRepository classificationRepository;

    public ClassificationSeeder(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (this.classificationRepository.count() == 0) {
            Arrays.stream(ClassificationNameEnum.values())
                    .forEach(classificationNameEnum -> {
                        this.classificationRepository.save(new Classification(classificationNameEnum));
                    });
        }
    }
}
