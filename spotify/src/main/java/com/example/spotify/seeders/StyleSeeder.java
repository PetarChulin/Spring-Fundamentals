package com.example.spotify.seeders;

import com.example.spotify.model.entity.Style;
import com.example.spotify.model.entity.StyleEnum;
import com.example.spotify.repositories.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StyleSeeder implements CommandLineRunner {

    private StyleRepository styleRepository;

    public StyleSeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    @Override
    public void run(String... args) {

        if (this.styleRepository.count() == 0) {
            Arrays.stream(StyleEnum.values())
                    .forEach(styleEnum -> {
                        this.styleRepository.save(new Style(styleEnum, String.format("Description for %s"
                                , styleEnum.name())));
                    });
        }
    }

}
