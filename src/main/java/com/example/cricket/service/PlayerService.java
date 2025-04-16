
package com.example.cricket.service;

import com.example.cricket.entity.Player;
import com.example.cricket.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> searchPlayersByFullName(String fName, String lName) {
        return playerRepository.findAllByFNameAndLName(fName, lName);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }
}


