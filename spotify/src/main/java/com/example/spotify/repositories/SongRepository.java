package com.example.spotify.repositories;

import com.example.spotify.model.entity.Song;
import com.example.spotify.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByStyle_Style(StyleEnum name);


}
