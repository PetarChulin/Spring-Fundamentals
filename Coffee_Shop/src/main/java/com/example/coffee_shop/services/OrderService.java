package com.example.coffee_shop.services;

import com.example.coffee_shop.model.dto.OrderDTO;
import com.example.coffee_shop.model.dto.OrdersDTO;
import com.example.coffee_shop.model.dto.UserDTO;
import com.example.coffee_shop.model.dto.UsersDTO;
import com.example.coffee_shop.model.entity.Category;
import com.example.coffee_shop.model.entity.CategoryEnum;
import com.example.coffee_shop.model.entity.Order;
import com.example.coffee_shop.model.entity.User;
import com.example.coffee_shop.repositories.CategoryRepository;
import com.example.coffee_shop.repositories.OrderRepository;
import com.example.coffee_shop.repositories.UserRepository;
import com.example.coffee_shop.sessions.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final LoggedUser loggedUser;
    private CategoryRepository categoryRepository;


    @Autowired
    public OrderService(UserRepository userRepository, OrderRepository orderRepository, LoggedUser loggedUser, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.loggedUser = loggedUser;
        this.categoryRepository = categoryRepository;
    }


    public boolean create(OrderDTO orderDTO) {

        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Optional<Category> category = this.categoryRepository.findByName(CategoryEnum.valueOf(orderDTO.getCategory().name()));

        Order order = new Order();

        order.setName(orderDTO.getName());
        order.setPrice(orderDTO.getPrice());
        order.setOrderTime(orderDTO.getOrderTime());
        order.setCategory(category.get());
        order.setDescription(orderDTO.getDescription());
        order.setEmployee(user.get());

        this.orderRepository.save(order);

        return true;
    }


    public List<OrdersDTO> findAllOrderByPrice(){
        return orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(OrdersDTO::new).collect(Collectors.toList());
    }


    public List<UsersDTO> findOrdersByEmployee() {
        return userRepository.findAllByOrdersCountDesc()
                .stream()
                .map(user -> {
                    UsersDTO usersDTO = new UsersDTO();
                    usersDTO.setUsername(user.getUsername());
                    usersDTO.setCountOrders(user.getOrders().size());

                    return usersDTO;
                }).collect(Collectors.toList());


    }


    public void readyOrder(Long id) {

        orderRepository.deleteById(id);
    }
}
