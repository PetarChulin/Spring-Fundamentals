package com.example.spotify.controller;

import com.example.spotify.model.dto.SongDTO;
import com.example.spotify.model.entity.Song;
import com.example.spotify.model.entity.StyleEnum;
import com.example.spotify.model.entity.User;
import com.example.spotify.repositories.SongRepository;
import com.example.spotify.repositories.UserRepository;
import com.example.spotify.service.AuthService;
import com.example.spotify.service.SongService;
import com.example.spotify.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private AuthService authService;
    private LoggedUser loggedUser;
    private SongRepository songRepository;
    private SongService songsService;
    private UserRepository userRepository;

    public HomeController(AuthService authService, LoggedUser loggedUser, SongRepository songRepository, SongService songsService, UserRepository userRepository) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.songRepository = songRepository;
        this.songsService = songsService;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }
        List<SongDTO> rockSongs = this.songsService.findSongsByStyle(StyleEnum.ROCK);
        List<SongDTO> jazzSongs = this.songsService.findSongsByStyle(StyleEnum.JAZZ);
        List<SongDTO> popSongs = this.songsService.findSongsByStyle(StyleEnum.POP);
        List<Song> playlist = this.userRepository.getUserPlaylist(loggedUser.getId());
        int timeInSec = playlist
                .stream()
                .map(Song::getDuration)
                .reduce(Integer::sum).orElse(0);

        int seconds = timeInSec % 60;
        Integer minutes = (timeInSec / 60) % 60;

        String totalTime = "0";
        if (timeInSec > 0) {
            totalTime = String.format(minutes + ":" + seconds);
        }

        model.addAttribute("rockSongs", rockSongs);
        model.addAttribute("jazzSongs", jazzSongs);
        model.addAttribute("popSongs", popSongs);
        model.addAttribute("playlist", playlist);
        model.addAttribute("totalTime", totalTime);

        return "home";
    }
}
