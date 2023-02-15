package com.example.spotify.controller;

import com.example.spotify.model.dto.SongDTO;
import com.example.spotify.repositories.SongRepository;
import com.example.spotify.repositories.UserRepository;
import com.example.spotify.service.AuthService;
import com.example.spotify.service.SongService;
import com.example.spotify.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongController {

    private final AuthService authService;

    private SongRepository songRepository;

    private UserRepository userRepository;

    private LoggedUser loggedUser;
    private SongService songService;

    public SongController(AuthService authService, SongRepository songRepository, UserRepository userRepository, LoggedUser loggedUser, SongService songService) {
        this.authService = authService;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.songService = songService;
    }

    @ModelAttribute("songDTO")
    public SongDTO initSongDTO(){return new SongDTO();}

    @GetMapping("/songs/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "song-add";
    }

    @PostMapping("/songs/add")
    public String posts(@Valid SongDTO songDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.songService.add(songDTO)) {
            attributes.addFlashAttribute("songDTO", songDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.songDTO",
                    result);
            return "redirect:/songs/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/playlist/add/{id}")
    public String add(@PathVariable Long id){

        this.songService.addToPlaylist(id);

        return "redirect:/home";
    }

    @GetMapping("/playlist")

    public String removeAll(){

        songService.clearPlaylist();

        return "redirect:/home";
    }


}
