package com.example.cricket.controller;

import com.example.cricket.entity.Team;
import com.example.cricket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Optional<Team> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping
    public Team addTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/{id}")
    public String deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return "Team deleted successfully!";
    }
}
