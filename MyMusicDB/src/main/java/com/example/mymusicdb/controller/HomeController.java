package com.example.mymusicdb.controller;

import com.example.mymusicdb.model.entity.Album;
import com.example.mymusicdb.repositories.AlbumRepository;
import com.example.mymusicdb.services.AuthService;
import com.example.mymusicdb.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private AuthService authService;
    private LoggedUser loggedUser;
    private AlbumRepository albumRepository;

    public HomeController(AuthService authService, LoggedUser loggedUser, AlbumRepository albumRepository) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.albumRepository = albumRepository;
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }
        List<Album> albums = this.albumRepository.findAllByOrderByCopiesDesc();

        model.addAttribute("albums" , albums);
        model.addAttribute("totalSoldCopies" , albums
                .stream()
                .map(Album::getCopies)
                .reduce(Integer::sum).orElse(0));

        return "home";
    }
}
