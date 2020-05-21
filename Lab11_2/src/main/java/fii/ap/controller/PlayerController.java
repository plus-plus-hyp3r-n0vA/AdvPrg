package fii.ap.controller;

import fii.ap.entity.Player;
import fii.ap.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        playerRepository.save(player);
        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateName(@PathVariable int id, @RequestParam String name) {
        var player = playerRepository.findById(id).orElse(null);
        if(player == null)
            throw new PlayerNotFoundException("Player not found!");
        player.setName(name);
        playerRepository.save(player);
        return new ResponseEntity<>("Player name update successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        var player = playerRepository.findById(id).orElse(null);
        if (player == null)
            throw new PlayerNotFoundException("Player not found!");
        playerRepository.delete(player);
        return new ResponseEntity<>("Player removed", HttpStatus.OK);
    }

}

