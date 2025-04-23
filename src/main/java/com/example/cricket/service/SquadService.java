package com.example.cricket.service;

import com.example.cricket.entity.Squad;
import com.example.cricket.repository.SquadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquadService {

    @Autowired
    private SquadRepository squadRepository;

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
        return squadRepository.save(squad);
    }

    public void deleteSquad(Long squadId) {
        squadRepository.deleteById(squadId);
    }
}