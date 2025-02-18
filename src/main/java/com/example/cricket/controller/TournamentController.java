package com.example.cricket.controller;

import com.example.cricket.entity.Tournament;
import com.example.cricket.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping
    public Tournament addTournament(@RequestBody Tournament tournament) {
        return tournamentService.saveTournament(tournament);
    }

    @DeleteMapping("/{id}")
    public String deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
        return "Tournament deleted successfully!";
    }
}
