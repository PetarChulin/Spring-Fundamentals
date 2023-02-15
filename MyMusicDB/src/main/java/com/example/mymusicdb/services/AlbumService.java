package com.example.mymusicdb.services;

import com.example.mymusicdb.model.dto.AlbumDTO;
import com.example.mymusicdb.model.entity.Album;
import com.example.mymusicdb.model.entity.Artist;
import com.example.mymusicdb.model.entity.SingerEnum;
import com.example.mymusicdb.model.entity.User;
import com.example.mymusicdb.repositories.AlbumRepository;
import com.example.mymusicdb.repositories.ArtistRepository;
import com.example.mymusicdb.repositories.UserRepository;
import com.example.mymusicdb.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumService {


    private UserRepository userRepository;

    private LoggedUser loggedUser;
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;

    public AlbumService(UserRepository userRepository, LoggedUser loggedUser, AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public boolean add(AlbumDTO albumDTO) {

        Optional<User> userId = this.userRepository.findById(this.loggedUser.getId());
        Optional<Artist> artistName = this.artistRepository.findArtistByName(SingerEnum.valueOf(albumDTO.getSinger().name()));


        Album album = new Album();

        album.setName(albumDTO.getName());
        album.setImageUrl(albumDTO.getImageUrl());
        album.setCopies(albumDTO.getCopies());
        album.setGenre(albumDTO.getGenre());
        album.setPrice(albumDTO.getPrice());
        album.setReleasedDate(albumDTO.getReleasedDate());
        album.setProducer(albumDTO.getProducer());
        album.setArtist(artistName.get());
        album.setAddedFrom(userId.get());
        album.setDescription(albumDTO.getDescription());

        this.albumRepository.save(album);

        return true;
    }

    public void deleteItemById(Long id) {

        albumRepository.deleteById(id);
    }
}
