package com.example.andreys.repositories;

import com.example.andreys.model.dto.ItemsDTO;
import com.example.andreys.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAll();

    Optional<Item> findByName(String name);

}
