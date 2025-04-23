package com.example.cricket.service;

import com.example.cricket.entity.Game;
import com.example.cricket.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cricket.entity.Team;
import com.example.cricket.repository.TeamRepository;



import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game saveGame(Game game) {
        // Fetch the actual Team objects from DB using the IDs provided in JSON
        Team team1 = teamRepository.findById(game.getTeam1().getTeamId()).orElseThrow();
        Team team2 = teamRepository.findById(game.getTeam2().getTeamId()).orElseThrow();

        Team tossWinner = null;
        if (game.getTossWinner() != null && game.getTossWinner().getTeamId() != null) {
            tossWinner = teamRepository.findById(game.getTossWinner().getTeamId()).orElseThrow();
        }

        Team winner = null;
        if (game.getWinnerTeam() != null && game.getWinnerTeam().getTeamId() != null) {
            winner = teamRepository.findById(game.getWinnerTeam().getTeamId()).orElse(null);
        }

        // Set the actual entities back
        game.setTeam1(team1);
        game.setTeam2(team2);
        game.setTossWinner(tossWinner);
        game.setWinnerTeam(winner);

        return gameRepository.save(game);
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }
}
