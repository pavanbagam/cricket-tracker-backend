package com.example.cricket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;


@Entity
@Table(
    name = "squad",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_player_team", columnNames = {"player_id", "team_id"})
    }
)
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

    // lets Hibernate treat (player,team) as a natural key
    @NaturalId
    public Team getTeam()   { return team; }
    @NaturalId
    public Player getPlayer(){ return player; }


    

    public void setTeam(Team team) {
        this.team = team;
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
