package com.example.andreys.controllers;

import com.example.andreys.model.dto.ItemDTO;
import com.example.andreys.model.entity.Item;
import com.example.andreys.repositories.ItemRepository;
import com.example.andreys.services.AuthService;
import com.example.andreys.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {

    private final AuthService authService;
    private final ItemService itemService;

    private ItemRepository itemRepository;


    public ItemController(AuthService authService, ItemService itemService, ItemRepository itemRepository) {
        this.authService = authService;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @ModelAttribute("itemDTO")
    public ItemDTO initItemDTO(){return new ItemDTO();}

    @GetMapping("/items/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "add-item";
    }

    @GetMapping("/items/details/{id}")
    public ModelAndView details(@PathVariable Long id , ModelAndView model) {

        Item item = this.itemRepository.findById(id).orElseThrow();

        model.setViewName("details-item");
        model.addObject("item", item);
        return model;
    }

    @PostMapping("/items/add")
    public String items(@Valid ItemDTO itemDTO,
                           BindingResult result,
                           RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.itemService.add(itemDTO)) {
            attributes.addFlashAttribute("itemDTO", itemDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.itemDTO",
                    result);
            return "redirect:/items/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/items/delete/{id}")
    public String delete(@PathVariable Long id){
        itemService.deleteItemById(id);

        return "redirect:/home";
    }

}
