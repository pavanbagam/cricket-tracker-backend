package com.example.cricket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "performance")
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    private int gamesPlayed;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getInningsPlayed() {
        return inningsPlayed;
    }

    public void setInningsPlayed(int inningsPlayed) {
        this.inningsPlayed = inningsPlayed;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getDeliveriesFaced() {
        return deliveriesFaced;
    }

    public void setDeliveriesFaced(int deliveriesFaced) {
        this.deliveriesFaced = deliveriesFaced;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public double getBattingStrikeRate() {
        return battingStrikeRate;
    }

    public void setBattingStrikeRate(double battingStrikeRate) {
        this.battingStrikeRate = battingStrikeRate;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(double battingAverage) {
        this.battingAverage = battingAverage;
    }

    public int getNotOuts() {
        return notOuts;
    }

    public void setNotOuts(int notOuts) {
        this.notOuts = notOuts;
    }

    public int getDeliveriesBowled() {
        return deliveriesBowled;
    }

    public void setDeliveriesBowled(int deliveriesBowled) {
        this.deliveriesBowled = deliveriesBowled;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(int runsGiven) {
        this.runsGiven = runsGiven;
    }

    public double getBowlingStrikeRate() {
        return bowlingStrikeRate;
    }

    public void setBowlingStrikeRate(double bowlingStrikeRate) {
        this.bowlingStrikeRate = bowlingStrikeRate;
    }

    public double getBowlingAverage() {
        return bowlingAverage;
    }

    public void setBowlingAverage(double bowlingAverage) {
        this.bowlingAverage = bowlingAverage;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    private int inningsPlayed;
    private int runsScored;
    private int deliveriesFaced;
    private int fours;
    private int sixes;
    private double battingStrikeRate;
    private double battingAverage;
    private int notOuts;
    private int deliveriesBowled;
    private int runsGiven;
    private double bowlingStrikeRate;
    private double bowlingAverage;
    private int wicketsTaken;
}
