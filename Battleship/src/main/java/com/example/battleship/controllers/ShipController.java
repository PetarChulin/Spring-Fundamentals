package com.example.battleship.controllers;

import com.example.battleship.models.dto.ShipDTO;
import com.example.battleship.services.AuthService;
import com.example.battleship.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    private final ShipService shipService;
    private final AuthService authService;

    public ShipController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }

    @ModelAttribute("shipDTO")
    public ShipDTO initShipDTO() {
        return new ShipDTO();
    }

    @GetMapping("/ships/add")
    public String ships() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid ShipDTO shipDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.shipService.create(shipDTO)) {
            attributes.addFlashAttribute("shipDTO", shipDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.shipDTO",
                    result);
            return "redirect:/ships/add";
        }

        return "redirect:/home";

    }
}
