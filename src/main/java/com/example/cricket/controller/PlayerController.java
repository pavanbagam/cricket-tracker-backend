package com.example.cricket.controller;

import com.example.cricket.entity.Player;
import com.example.cricket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;



    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    
    @GetMapping("/search")
    public ResponseEntity<List<Player>> getPlayerByFullName(
            @RequestParam String fName,
            @RequestParam String lName) {

        List<Player> players = playerService.searchPlayersByFullName(fName, lName);

        if (players.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(players);
        }
    }

    @PostMapping
    public Player addPlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }
}
