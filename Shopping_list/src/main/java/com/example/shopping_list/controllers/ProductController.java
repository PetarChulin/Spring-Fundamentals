package com.example.shopping_list.controllers;

import com.example.shopping_list.model.dto.ProductDTO;
import com.example.shopping_list.services.AuthService;
import com.example.shopping_list.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final AuthService authService;

    private final ProductService productService;

    public ProductController(AuthService authService, ProductService productService) {
        this.authService = authService;
        this.productService = productService;
    }

    @ModelAttribute("productDTO")
    public ProductDTO initProductDTO(){return new ProductDTO();}

    @GetMapping("/products/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "product-add";
    }

    @PostMapping("/products/add")
    public String products(@Valid ProductDTO productDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.productService.add(productDTO)) {
            attributes.addFlashAttribute("productDTO", productDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.productDTO",
                    result);
            return "redirect:/products/add";
        }

        return "redirect:/home";

    }

    @GetMapping("/products/buy/{id}")
    public String buy(@PathVariable Long id){
        productService.buyProduct(id);

        return "redirect:/home";
    }

    @GetMapping("/products")
    public String buyAll(){
        productService.buyAllProducts();

        return "redirect:/home";
    }
}
