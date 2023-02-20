package com.example.exam.repositories;

import com.example.exam.model.entity.Offer;
import com.example.exam.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> getUserById(Long id);

    User getUsersById(long id);

    @Query("select b.boughtOffers  from User as b where b.id= :id")
    List<Offer> getUserBoughtOffers(@Param("id") Long id);

//    @Query("select b.offers  from User as b where b.id= :id")
//    List<Offer> getUserOffers(@Param("id") Long id);
//
//    @Query("select b.offers  from User as b where b.id!= :id")
//    List<Offer> getOthersOffers(@Param("id") Long id);
}
