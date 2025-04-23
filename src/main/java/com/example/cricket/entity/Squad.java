package com.example.cricket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "squad")
@NoArgsConstructor
@AllArgsConstructor
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long squadId;

    
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    private Long tournamentId;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }
}
