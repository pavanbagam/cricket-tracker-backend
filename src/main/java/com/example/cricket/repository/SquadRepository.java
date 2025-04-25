package com.example.cricket.repository;

import com.example.cricket.entity.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.example.cricket.dto.PlayerMiniDTO;


// SquadRepository.java
@Repository
public interface SquadRepository extends JpaRepository<Squad,Long> {

    /* full squad lists */
    List<Squad> findByTeamTeamId(Long teamId);
    List<Squad> findByTournamentId(Long tournamentId);
    List<Squad> findByPlayerPlayerId(Long playerId);

    /* mini helpers */
    @Query("select count(s) from Squad s where s.team.teamId = :teamId")
    long countByTeamId(@Param("teamId") Long teamId);      // <- rename to countByTeamId

    @Query("""
           select new com.example.cricket.dto.PlayerMiniDTO(
                    s.player.playerId,
                    concat(s.player.fName,' ',s.player.lName))
           from Squad s
           where s.team.teamId = :teamId
           """)
    List<PlayerMiniDTO> squadMiniByTeam(@Param("teamId") Long teamId);   // <- projection query
}
