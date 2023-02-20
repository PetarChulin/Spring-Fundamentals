package com.example.exam.repositories;

import com.example.exam.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

        Optional<Offer> findById(Long id);

        List<Offer> findByCreatedById(Long id);

        List<Offer> findByCreatedByIdNot(Long id);

        List<Offer> findAll();









}
