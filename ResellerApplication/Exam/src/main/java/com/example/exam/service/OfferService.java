package com.example.exam.service;

import com.example.exam.model.dto.OfferDTO;
import com.example.exam.model.entity.Condition;
import com.example.exam.model.entity.ConditionEnum;
import com.example.exam.model.entity.Offer;
import com.example.exam.model.entity.User;
import com.example.exam.repositories.ConditionRepository;
import com.example.exam.repositories.OfferRepository;
import com.example.exam.repositories.UserRepository;
import com.example.exam.session.LoggedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {



    private OfferRepository offerRepository;

    private ConditionRepository conditionRepository;
    private UserRepository userRepository;
    private LoggedUser loggedUser;

    public OfferService(OfferRepository offerRepository, ConditionRepository conditionRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean add(OfferDTO offerDTO) {

        Optional<Condition> condition = this.conditionRepository.findByName(ConditionEnum.valueOf(offerDTO.getCondition().name()));
        User user = this.userRepository.getUsersById(this.loggedUser.getId());


        Offer offer = new Offer();


        offer.setDescription(offerDTO.getDescription());
        offer.setPrice(offerDTO.getPrice());
        offer.setCondition(condition.get());
        offer.setCreatedBy(user);


        this.offerRepository.save(offer);
        return true;
    }

    public void removeOfferById(Long id) {

        this.offerRepository.deleteById(id);
    }

    @Transactional
    public void buyOffer(Long id) {


        Optional<Offer> offer = this.offerRepository.findById(id);
        User user = this.userRepository.getUsersById(this.loggedUser.getId());

        offer.get().setOwner(user);
        offer.get().setCreatedBy(null);

    }

}
