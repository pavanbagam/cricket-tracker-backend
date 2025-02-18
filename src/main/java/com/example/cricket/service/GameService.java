package com.example.cricket.service;

import com.example.cricket.entity.Game;
import com.example.cricket.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public Optional<Game> getMatchById(Long id) {
        return gameRepository.findById(id);
    }
}
