package com.example.cricket.controller;

import com.example.cricket.entity.Game;
import com.example.cricket.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.cricket.service.InningService;
import com.example.cricket.entity.Inning;
import com.example.cricket.service.SquadService;
import com.example.cricket.entity.Team;

import java.util.List;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private InningService inningService;

    @Autowired
    private SquadService squadService;

    @GetMapping
    public List<Game> getGames() {
        return gameService.getAllGames();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameService.getGameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    @PostMapping
    public Game addGame(@RequestBody Game game) {
        // create innings for the teams involved in the game
        Game savedGame = gameService.saveGame(game);
        Inning inning1 = new Inning();
        Inning inning2 = new Inning();
        inning1.setGame(game);
        inning2.setGame(game);
        inning1.setTotalRuns(0);
        inning2.setTotalRuns(0);
        inning1.setCurrOvers(0);
        inning2.setCurrOvers(0);
        inning1.setOvers(game.getOvers());
        inning2.setOvers(game.getOvers());
        inning1.setDeliveries(0);
        inning2.setDeliveries(0);

        if(game.getTossWinner().getTeamId() == game.getTeam1().getTeamId()) {
            if(game.isChoseToBat())
            {
                
                inning1.setTeam(game.getTeam1());
                inning1.setWickets(squadService.getSquadsByTeamId(game.getTeam1().getTeamId()).size()); // Assuming all players are available at the start
                inning2.setTeam(game.getTeam2());
                inning2.setWickets(squadService.getSquadsByTeamId(game.getTeam2().getTeamId()).size()); // Assuming all players are available at the start
            }
            else {
                inning1.setTeam(game.getTeam1());
                inning1.setWickets(squadService.getSquadsByTeamId(game.getTeam1().getTeamId()).size()); // Assuming all players are available at the start
                inning2.setTeam(game.getTeam2());
                inning2.setWickets(squadService.getSquadsByTeamId(game.getTeam2().getTeamId()).size()); // Assuming all players are available at the start
            }
        } else {
            if(game.isChoseToBat())
            {
                inning1.setTeam(game.getTeam1());
                inning1.setWickets(squadService.getSquadsByTeamId(game.getTeam1().getTeamId()).size()); // Assuming all players are available at the start
                inning2.setTeam(game.getTeam2());
                inning2.setWickets(squadService.getSquadsByTeamId(game.getTeam2().getTeamId()).size()); // Assuming all players are available at the start
            }
            else {
                inning1.setTeam(game.getTeam1());
                inning1.setWickets(squadService.getSquadsByTeamId(game.getTeam1().getTeamId()).size()); // Assuming all players are available at the start
                inning2.setTeam(game.getTeam2());
                inning2.setWickets(squadService.getSquadsByTeamId(game.getTeam2().getTeamId()).size()); // Assuming all players are available at the start
            }
        }
        inningService.saveInning(inning1);
        inningService.saveInning(inning2);

        return savedGame;
    }
}
