package com.example.coffee_shop.controllers;

import com.example.coffee_shop.model.dto.OrdersDTO;
import com.example.coffee_shop.model.dto.UserDTO;
import com.example.coffee_shop.model.dto.UsersDTO;
import com.example.coffee_shop.services.AuthService;
import com.example.coffee_shop.services.OrderService;
import com.example.coffee_shop.sessions.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private AuthService authService;
    private LoggedUser loggedUser;
    private OrderService orderService;

    @Autowired
    public HomeController(AuthService authService, LoggedUser loggedUser, OrderService orderService) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.orderService = orderService;
    }

    @ModelAttribute("orders")
    public OrdersDTO ordersDTO() {
        return new OrdersDTO();
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }

//        long userId = this.loggedUser.getId();
        List<OrdersDTO> orders = this.orderService.findAllOrderByPrice();
        List<UsersDTO> users = this.orderService.findOrdersByEmployee();

        model.addAttribute("orders", orders);
        model.addAttribute("totalTime" , orders
                .stream()
                .map(ordersDTO -> ordersDTO.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(0));
        model.addAttribute("users", users);
        return "home";

    }
}
