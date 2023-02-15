package com.example.mymusicdb.seeders;

import com.example.mymusicdb.model.entity.Artist;
import com.example.mymusicdb.model.entity.SingerEnum;
import com.example.mymusicdb.repositories.ArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ArtistSeeder implements CommandLineRunner {

    private final ArtistRepository artistRepository;

    public ArtistSeeder(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.artistRepository.count() == 0) {
            Arrays.stream(SingerEnum.values())
                    .forEach(singerEnum -> {
                        this.artistRepository.save(new Artist(singerEnum, String.format("Information for %s"
                                , singerEnum.name())));
                    });
        }
    }
}
