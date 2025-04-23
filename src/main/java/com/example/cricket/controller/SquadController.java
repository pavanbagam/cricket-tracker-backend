package com.example.cricket.controller;

import com.example.cricket.entity.Squad;
import com.example.cricket.service.SquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/squads")
public class SquadController {

    @Autowired
    private SquadService squadService;

    @GetMapping
    public List<Squad> getAllSquads() {
        return squadService.getAllSquads();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Squad> getSquadById(@PathVariable Long id) {
        return squadService.getSquadById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/team/{teamId}")
    public List<Squad> getSquadsByTeamId(@PathVariable Long teamId) {
        return squadService.getSquadsByTeamId(teamId);
    }

    @GetMapping("/tournament/{tournamentId}")
    public List<Squad> getSquadsByTournamentId(@PathVariable Long tournamentId) {
        return squadService.getSquadsByTournamentId(tournamentId);
    }

    @GetMapping("/player/{playerId}")
    public List<Squad> getSquadsByPlayerId(@PathVariable Long playerId) {
        return squadService.getSquadsByPlayerId(playerId);
    }

    @PostMapping
    public Squad addSquad(@RequestBody Squad squad) {
        return squadService.saveSquad(squad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSquad(@PathVariable Long id) {
        squadService.deleteSquad(id);
        return ResponseEntity.ok("Squad deleted successfully!");
    }
}