package com.example.cricket.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "delivery")

@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "inning_id", nullable = false)
    private Inning inning;

    public Inning getInning() {
        return inning;
    }

    public void setInning(Inning inning) {
        this.inning = inning;
    }

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public Player getBatsman() {
        return batsman;
    }

    public void setBatsman(Player batsman) {
        this.batsman = batsman;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }

    public Player getFielder() {
        return fielder;
    }

    public void setFielder(Player fielder) {
        this.fielder = fielder;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public boolean isWicket() {
        return isWicket;
    }

    public String getDismissalType() {
        return dismissalType;
    }

    public void setDismissalType(String dismissalType) {
        this.dismissalType = dismissalType;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public void setBallNumber(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public void setOverNumber(int overNumber) {
        this.overNumber = overNumber;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bowler_id", nullable = false)
    private Player bowler;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "batsman_id", nullable = false)
    private Player batsman;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "non_striker_id")
    private Player nonStriker;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fielder_id")
    private Player fielder;

    private int runsScored;
    private boolean isWicket;
    private String dismissalType;
    private int ballNumber;
    private int overNumber;
    private boolean isWideOrNoBall;

    public void setWicket(boolean wicket) {
        isWicket = wicket;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public boolean isWideOrNoBall() {
        return isWideOrNoBall;
    }

    public void setWideOrNoBall(boolean wideOrNoBall) {
        isWideOrNoBall = wideOrNoBall;
    }
}
