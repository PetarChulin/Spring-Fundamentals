package com.example.battleship.controllers;

//import org.springframework.ui.Model;
import com.example.battleship.models.dto.ShipsDTO;
import com.example.battleship.models.dto.StartBattleDTO;
import com.example.battleship.services.AuthService;
import com.example.battleship.services.ShipService;
import com.example.battleship.sessions.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final LoggedUser loggedUser;
    private AuthService authService;

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initBattleForm() {return new StartBattleDTO();}

    @Autowired
    public HomeController(ShipService shipService, LoggedUser loggedUser, AuthService authService) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
        this.authService = authService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String loggedIn(Model model) {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        long loggedUserId = this.loggedUser.getId();
        List<ShipsDTO> ownShips = this.shipService.getOwnShips(loggedUserId);
        List<ShipsDTO> enemyShips = this.shipService.getEnemyShips(loggedUserId);
        List<ShipsDTO> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("ownShips" , ownShips);
        model.addAttribute("enemyShips" , enemyShips);
        model.addAttribute("sortedShips" , sortedShips);

        return "home";
    }
}
