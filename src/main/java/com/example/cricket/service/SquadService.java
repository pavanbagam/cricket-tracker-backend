package com.example.cricket.service;

import com.example.cricket.entity.Squad;
import com.example.cricket.repository.SquadRepository;
import com.example.cricket.entity.Player;
import com.example.cricket.repository.PlayerRepository;
import com.example.cricket.entity.Team;
import com.example.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquadService {

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Squad> getAllSquads() {
        return squadRepository.findAll();
    }

    public Optional<Squad> getSquadById(Long squadId) {
        return squadRepository.findById(squadId);
    }

    public List<Squad> getSquadsByTeamId(Long teamId) {
        return squadRepository.findByTeamTeamId(teamId);
    }

    public List<Squad> getSquadsByTournamentId(Long tournamentId) {
        return squadRepository.findByTournamentId(tournamentId);
    }

    public List<Squad> getSquadsByPlayerId(Long playerId) {
        return squadRepository.findByPlayerPlayerId(playerId);
    }

    public Squad saveSquad(Squad squad) {

        if (squad.getPlayer() == null || squad.getPlayer().getPlayerId() == null) {
            throw new IllegalArgumentException("Player data is missing or incomplete");
        }

        if (squad.getTeam() == null || squad.getTeam().getTeamId() == null) {
            throw new IllegalArgumentException("Team data is missing or incomplete");
        }
        // Validate Player
        Player player = playerRepository.findById(squad.getPlayer().getPlayerId())
                .orElseThrow(() -> new IllegalArgumentException("Player with ID " + squad.getPlayer().getPlayerId() + " does not exist"));

        // Validate Team
        Team team = teamRepository.findById(squad.getTeam().getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team with ID " + squad.getTeam().getTeamId() + " does not exist"));

        // Set validated Player and Team
        squad.setPlayer(player);
        squad.setTeam(team);

        return squadRepository.save(squad);
    }

    public void deleteSquad(Long squadId) {
        squadRepository.deleteById(squadId);
    }
}