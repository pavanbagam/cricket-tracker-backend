package com.example.cricket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@Entity
@Table(
    name = "game",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_match_code", columnNames = "match_code")
    }
)
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("gameId")
    private Long gameId;

    private Date date;
    private String venue;
    private String type;
    private String umpire;

    @Column(name = "match_code", nullable = false, length = 40)
    private String matchCode; // Unique match code for the game

    @ManyToOne
    @JoinColumn(name = "toss_winner_id")
    private Team tossWinner;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;
    

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Team getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(Team tossWinner) {
        this.tossWinner = tossWinner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUmpire() {
        return umpire;
    }

    public void setUmpire(String umpire) {
        this.umpire = umpire;
    }

    
    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }
}
