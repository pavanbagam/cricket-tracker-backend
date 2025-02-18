package com.example.cricket.controller;

import com.example.cricket.entity.Game;
import com.example.cricket.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getGames() {
        return gameService.getAllGames();
    }

    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }
}
