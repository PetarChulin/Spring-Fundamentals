package com.example.likebook.repositories;

import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.MoodEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoodRepository extends JpaRepository<Mood, Long> {

    Optional<Mood> findByName(MoodEnum name);
}
