package com.example.shopping_list.services;

import com.example.shopping_list.model.dto.ProductDTO;
import com.example.shopping_list.model.dto.ProductsDTO;
import com.example.shopping_list.model.entity.Category;
import com.example.shopping_list.model.entity.CategoryEnum;
import com.example.shopping_list.model.entity.Product;
import com.example.shopping_list.model.entity.User;
import com.example.shopping_list.repositories.CategoryRepository;
import com.example.shopping_list.repositories.ProductRepository;
import com.example.shopping_list.repositories.UserRepository;
import com.example.shopping_list.sessions.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final LoggedUser loggedUser;

    private CategoryRepository categoryRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository, LoggedUser loggedUser, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.loggedUser = loggedUser;
        this.categoryRepository = categoryRepository;
    }

    public boolean add(ProductDTO productDTO) {

        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Optional<Category> category = this.categoryRepository.findByName(CategoryEnum.valueOf(productDTO.getCategory().name()));

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category.get());
        product.setDescription(productDTO.getDescription());
        product.setNeededBefore(productDTO.getNeededBefore());


        this.productRepository.save(product);

        return true;
    }

    public void buyProduct(Long id) {

        productRepository.deleteById(id);
    }
    public void buyAllProducts() {

        productRepository.deleteAll();
    }


    public List<ProductsDTO> findProductsByCategory(CategoryEnum name){
        return this.productRepository.findAllByCategory_Name(name)
                .stream()
                .map(ProductsDTO::new).collect(Collectors.toList());
    }
}
