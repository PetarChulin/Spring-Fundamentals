package com.example.mymusicdb.repositories;

import com.example.mymusicdb.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {


    List<Album> findAllByOrderByCopiesDesc();

}
