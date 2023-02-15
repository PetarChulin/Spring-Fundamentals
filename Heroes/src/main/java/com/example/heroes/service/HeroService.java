package com.example.heroes.service;

import com.example.heroes.model.dto.HeroDTO;
import com.example.heroes.model.entity.Hero;
import com.example.heroes.model.entity.User;
import com.example.heroes.repositories.HeroRepository;
import com.example.heroes.repositories.UserRepository;
import com.example.heroes.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeroService {

    private UserRepository userRepository;

    private LoggedUser loggedUser;

    private HeroRepository heroRepository;

    public HeroService(UserRepository userRepository, LoggedUser loggedUser, HeroRepository heroRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.heroRepository = heroRepository;
    }

    public boolean add(HeroDTO heroDTO) {

        Optional<User> userId = this.userRepository.findById(this.loggedUser.getId());
        Optional<Hero> heroByName = this.heroRepository.findByName(heroDTO.getName());

        Hero hero = new Hero();

        if(heroByName.isPresent()){
            return false;
        }

        hero.setName(heroDTO.getName());
        hero.setType(heroDTO.getType());
        hero.setLevel(heroDTO.getLevel());

        this.heroRepository.save(hero);



        return true;
    }

    public void deleteHeroById(Long id) {

        heroRepository.deleteById(id);
    }
}
