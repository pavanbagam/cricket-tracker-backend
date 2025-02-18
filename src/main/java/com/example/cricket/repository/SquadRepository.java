package com.example.cricket.repository;

import com.example.cricket.entity.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {
    List<Squad> findByTeamTeamId(Long teamId);
    List<Squad> findByTournamentId(Long tournamentId);
    List<Squad> findByPlayerPlayerId(Long playerId);
}
