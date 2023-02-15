package com.example.battleship.services;

import com.example.battleship.models.dto.ShipDTO;
import com.example.battleship.models.dto.ShipsDTO;
import com.example.battleship.models.entity.Category;
import com.example.battleship.models.entity.Ship;
import com.example.battleship.models.entity.Type;
import com.example.battleship.models.entity.User;
import com.example.battleship.repos.CategoryRepo;
import com.example.battleship.repos.ShipRepository;
import com.example.battleship.repos.UserRepository;
import com.example.battleship.sessions.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private ShipRepository shipRepository;
    private CategoryRepo categoryRepo;

    private LoggedUser loggedUser;
    private UserRepository userRepository;

    public ShipService(ShipRepository shipRepository, CategoryRepo categoryRepo, LoggedUser loggedUser, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepo = categoryRepo;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(ShipDTO shipDTO) {

        Optional<Ship> byName =
                this.shipRepository.findByName(shipDTO.getName());

        if(byName.isPresent()){
            return false;
        }



        Type type = switch (shipDTO.getCategory()) {
            case 0 -> Type.BATTLE;
            case 1 -> Type.CARGO;
            case 2 -> Type.PATROL;
            default -> throw new IllegalStateException("Unexpected value: " + shipDTO.getCategory());
        };

        Category category = this.categoryRepo.findByName(type);
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());


        Ship ship = new Ship();
        ship.setName(shipDTO.getName());
        ship.setPower(shipDTO.getPower());
        ship.setHealth(shipDTO.getHealth());
        ship.setCreated(shipDTO.getCreated());
        ship.setCategory(category);
        ship.setUser(user.get());

        shipRepository.save(ship);

        return true;
    }

    public List<ShipsDTO> getOwnShips(long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
                .stream()
                .map(ShipsDTO::new).collect(Collectors.toList());
    }

    public List<ShipsDTO> getEnemyShips(long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId)
                .stream()
                .map(ShipsDTO::new).collect(Collectors.toList());
    }

    public List<ShipsDTO> getAllSorted() {
        return this.shipRepository.findByOrderByHealthAscNameDescPowerAsc()
                .stream()
                .map(ShipsDTO::new)
                .collect(Collectors.toList());
    }
}
