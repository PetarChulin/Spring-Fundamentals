package com.example.spotify.repositories;

import com.example.spotify.model.entity.Song;
import com.example.spotify.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    User getUsersById(long id);

    @Query("select s.songs from User as s where s.id= :id")
    List<Song> getUserPlaylist(@Param("id") Long id);



}
