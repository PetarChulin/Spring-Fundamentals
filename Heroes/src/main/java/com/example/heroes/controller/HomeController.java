package com.example.heroes.controller;

import com.example.heroes.model.entity.Hero;
import com.example.heroes.repositories.HeroRepository;
import com.example.heroes.service.AuthService;
import com.example.heroes.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private AuthService authService;
    private LoggedUser loggedUser;

    private HeroRepository heroRepository;

    public HomeController(AuthService authService, LoggedUser loggedUser, HeroRepository heroRepository) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.heroRepository = heroRepository;
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }
        List<Hero> heroes = this.heroRepository.findAllByOrderByLevelDesc();

        model.addAttribute("heroes" , heroes);

        return "home";
    }
}
