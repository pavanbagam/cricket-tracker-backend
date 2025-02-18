package com.example.cricket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inning")
@NoArgsConstructor
@AllArgsConstructor
public class Inning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inningId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    private int totalRuns;
    private int wickets;
    private int overs;
    private int deliveries;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public Long getInningId() {
        return inningId;
    }

    public void setInningId(Long inningId) {
        this.inningId = inningId;
    }

    public int getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(int deliveries) {
        this.deliveries = deliveries;
    }
}
