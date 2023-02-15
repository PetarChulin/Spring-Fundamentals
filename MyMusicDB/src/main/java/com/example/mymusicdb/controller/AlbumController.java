package com.example.mymusicdb.controller;

import com.example.mymusicdb.model.dto.AlbumDTO;
import com.example.mymusicdb.repositories.AlbumRepository;
import com.example.mymusicdb.repositories.UserRepository;
import com.example.mymusicdb.services.AlbumService;
import com.example.mymusicdb.services.AuthService;
import com.example.mymusicdb.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlbumController {

    private final AuthService authService;

    private AlbumRepository albumRepository;
    private final AlbumService albumService;
    private UserRepository userRepository;
    private LoggedUser loggedUser;

    public AlbumController(AuthService authService, AlbumRepository albumRepository, AlbumService albumService, UserRepository userRepository, LoggedUser loggedUser) {
        this.authService = authService;
        this.albumRepository = albumRepository;
        this.albumService = albumService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("albumDTO")
    public AlbumDTO initPostDTO(){return new AlbumDTO();}

    @GetMapping("/albums/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "add-album";
    }

    @PostMapping("/albums/add")
    public String posts(@Valid AlbumDTO albumDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.albumService.add(albumDTO)) {
            attributes.addFlashAttribute("albumDTO", albumDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.albumDTO",
                    result);
            return "redirect:/albums/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/albums/delete/{id}")
    public String delete(@PathVariable Long id){

        albumService.deleteItemById(id);

        return "redirect:/home";
    }
}
