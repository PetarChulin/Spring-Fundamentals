package com.example.shopping_list.controllers;

import com.example.shopping_list.model.dto.ProductsDTO;
import com.example.shopping_list.model.entity.CategoryEnum;
import com.example.shopping_list.model.entity.Product;
import com.example.shopping_list.repositories.ProductRepository;
import com.example.shopping_list.services.AuthService;
import com.example.shopping_list.services.ProductService;
import com.example.shopping_list.sessions.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {

    private AuthService authService;
    private LoggedUser loggedUser;
    private ProductService productService;

    private ProductRepository productRepository;

    @Autowired
    public HomeController(AuthService authService, LoggedUser loggedUser, ProductService productService, ProductRepository productRepository) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @ModelAttribute("products")
    public ProductsDTO productsDTODTO() {
        return new ProductsDTO();
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }
        List<Product> allProducts = this.productRepository.findAll();
        List<ProductsDTO> foods = this.productService.findProductsByCategory(CategoryEnum.Food);
        List<ProductsDTO> drinks = this.productService.findProductsByCategory(CategoryEnum.Drink);
        List<ProductsDTO> households = this.productService.findProductsByCategory(CategoryEnum.Household);
        List<ProductsDTO> others = this.productService.findProductsByCategory(CategoryEnum.Other);

        model.addAttribute("foods", foods);
        model.addAttribute("drinks", drinks);
        model.addAttribute("households", households);
        model.addAttribute("others", others);
        model.addAttribute("totalPrice", allProducts
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0)));


        return "home";

    }
}
