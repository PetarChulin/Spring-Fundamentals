package com.example.heroes.repositories;

import com.example.heroes.model.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    Optional<Hero> findByName(String name);

    List<Hero> findAllByOrderByLevelDesc();

}
