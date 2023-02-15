package com.example.coffee_shop.repositories;

import com.example.coffee_shop.model.entity.Order;
import com.example.coffee_shop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByEmployeeId(long ownerId);

    List<Order> findAllByOrderByPriceDesc();

}
