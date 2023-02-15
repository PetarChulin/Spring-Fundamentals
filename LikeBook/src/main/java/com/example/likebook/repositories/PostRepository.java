package com.example.likebook.repositories;

import com.example.likebook.model.dto.PostDTO;
import com.example.likebook.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findAll();

    List<Post> findByUserId(Long id);

    List<Post> findByUserIdNot(Long id);

    Optional<Post> findById(Long id);

}
