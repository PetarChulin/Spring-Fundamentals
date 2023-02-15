package com.example.mymusicdb.repositories;

import com.example.mymusicdb.model.entity.Artist;
import com.example.mymusicdb.model.entity.SingerEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findArtistByName(SingerEnum name);
}
