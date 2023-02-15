package com.example.spotify.repositories;

import com.example.spotify.model.entity.Style;
import com.example.spotify.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Optional<Style> findByStyle(StyleEnum name);
}
