package com.example.likebook.seeders;

import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.MoodEnum;
import com.example.likebook.repositories.MoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MoodSeeder implements CommandLineRunner {

    private MoodRepository moodRepository;


    public MoodSeeder(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        if (this.moodRepository.count() == 0) {
            Arrays.stream(MoodEnum.values())
                    .forEach(moodEnum -> {
                        this.moodRepository.save(new Mood(moodEnum, String.format("Description for %s"
                                , moodEnum.name())));
                    });
        }
    }
}
