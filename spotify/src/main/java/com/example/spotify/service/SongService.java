package com.example.spotify.service;

import com.example.spotify.model.dto.SongDTO;
import com.example.spotify.model.entity.Song;
import com.example.spotify.model.entity.Style;
import com.example.spotify.model.entity.StyleEnum;
import com.example.spotify.model.entity.User;
import com.example.spotify.repositories.SongRepository;
import com.example.spotify.repositories.StyleRepository;
import com.example.spotify.repositories.UserRepository;
import com.example.spotify.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SongService {

    private StyleRepository styleRepository;
    private SongRepository songRepository;
    private UserRepository userRepository;
    private LoggedUser loggedUser;


    public SongService(StyleRepository styleRepository, SongRepository songRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.styleRepository = styleRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean add(SongDTO songDTO) {

        Optional<Style> style = this.styleRepository.findByStyle(StyleEnum.valueOf(songDTO.getStyle()));

        Song song = new Song();

        song.setPerformer(songDTO.getPerformer());
        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setReleaseDate(songDTO.getReleaseDate());
        song.setStyle(style.get());

        this.songRepository.save(song);

        return true;

    }

    public List<SongDTO> findSongsByStyle(StyleEnum name) {
        return this.songRepository.findAllByStyle_Style(name)
                .stream()
                .map(SongDTO::new).collect(Collectors.toList());
    }

    public void addToPlaylist(Long id) {

        Song song = this.songRepository.findById(id).get();

        User user = this.userRepository.getUsersById(this.loggedUser.getId());

        user.addSong(song);

        this.userRepository.save(user);
    }

    public void clearPlaylist(){

        User user = this.userRepository.getUsersById(this.loggedUser.getId());

        user.setSongs(null);

        this.userRepository.save(user);

    }


}
