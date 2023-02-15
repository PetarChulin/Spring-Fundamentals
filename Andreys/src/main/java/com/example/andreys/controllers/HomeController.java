package com.example.andreys.controllers;

import com.example.andreys.model.dto.ItemsDTO;
import com.example.andreys.model.entity.Item;
import com.example.andreys.repositories.ItemRepository;
import com.example.andreys.services.AuthService;
import com.example.andreys.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private AuthService authService;
    private LoggedUser loggedUser;

    private ItemRepository itemRepository;

    public HomeController(AuthService authService, LoggedUser loggedUser, ItemRepository itemRepository) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }

        List<Item> items = this.itemRepository.findAll();

        model.addAttribute("items" , items);
        model.addAttribute("itemsCount" , items.size());

        return "home";
    }

}
