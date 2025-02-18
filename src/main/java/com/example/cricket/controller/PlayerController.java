package com.example.cricket.controller;

import com.example.cricket.entity.Player;
import com.example.cricket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Player addPlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }
}
