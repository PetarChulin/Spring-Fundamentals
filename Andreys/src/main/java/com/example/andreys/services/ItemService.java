package com.example.andreys.services;

import com.example.andreys.model.dto.ItemDTO;
import com.example.andreys.model.dto.ItemsDTO;
import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.CategoryEnum;
import com.example.andreys.model.entity.Item;
import com.example.andreys.repositories.CategoryRepository;
import com.example.andreys.repositories.ItemRepository;
import com.example.andreys.repositories.UserRepository;
import com.example.andreys.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    private final LoggedUser loggedUser;

    private CategoryRepository categoryRepository;


    public ItemService(UserRepository userRepository, ItemRepository itemRepository, LoggedUser loggedUser, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.loggedUser = loggedUser;
        this.categoryRepository = categoryRepository;
    }

    public boolean add(ItemDTO itemDTO) {

        Optional<Category> category = this.categoryRepository.findByName(CategoryEnum.valueOf(itemDTO.getCategory().name()));
        Optional<Item> itemByName = this.itemRepository.findByName(itemDTO.getName());

        if(itemByName.isPresent()) {
            return false;
        }
        Item item = new Item();

        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setCategory(category.get());
        item.setGender(itemDTO.getGender());

        this.itemRepository.save(item);

        return true;
    }

    public void deleteItemById(Long id) {

        itemRepository.deleteById(id);
    }




}
