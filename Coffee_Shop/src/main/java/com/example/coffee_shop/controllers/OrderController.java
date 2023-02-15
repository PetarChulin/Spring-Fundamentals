package com.example.coffee_shop.controllers;

import com.example.coffee_shop.model.dto.OrderDTO;
import com.example.coffee_shop.services.AuthService;
import com.example.coffee_shop.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final AuthService authService;

    private final OrderService orderService;

    public OrderController(AuthService authService, OrderService orderService) {
        this.authService = authService;
        this.orderService = orderService;
    }

    @ModelAttribute("orderDTO")
    public OrderDTO initOrderDTO(){return new OrderDTO();}

    @GetMapping("/orders/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "order-add";
    }

    @PostMapping("/orders/add")
    public String orders(@Valid OrderDTO orderDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.orderService.create(orderDTO)) {
            attributes.addFlashAttribute("orderDTO", orderDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDTO",
                    result);
            return "redirect:/orders/add";
        }

        return "redirect:/home";

    }

    @GetMapping("/orders/ready/{id}")
    public String ready(@PathVariable Long id){
        orderService.readyOrder(id);

        return "redirect:/home";
    }


}
