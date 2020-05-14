package fii.ap.controller;

import fii.ap.entity.Player;
import fii.ap.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}

