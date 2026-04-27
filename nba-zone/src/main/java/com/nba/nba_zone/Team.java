package com.nba.nba_zone;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String logoPath;
    private String headCoach;
    private Integer coachSince;

    private String conference;
    private String division;
    private String marketSize;

    private Integer playoffAppearances;
    private Integer nbaTitles;

    @Transient // Te pola nie muszą być w bazie, jeśli liczymy je "w locie"
    private Double totalSalary;
    @Transient
    private Double luxuryTax;
    @Transient
    private Integer teamOvr;
    @Transient
    private Integer playerCount;
    @Transient
    private Double averageAge;

    private Integer wins = 0;
    private Integer losses = 0;
    private Integer bonusPoints = 0;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(String headCoach) {
        this.headCoach = headCoach;
    }

    public Integer getCoachSince() {
        return coachSince;
    }

    public void setCoachSince(Integer coachSince) {
        this.coachSince = coachSince;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getMarketSize() {
        return marketSize;
    }

    public void setMarketSize(String marketSize) {
        this.marketSize = marketSize;
    }

    public Integer getPlayoffAppearances() {
        return playoffAppearances;
    }

    public void setPlayoffAppearances(Integer playoffAppearances) {
        this.playoffAppearances = playoffAppearances;
    }

    public Integer getNbaTitles() {
        return nbaTitles;
    }

    public void setNbaTitles(Integer nbaTitles) {
        this.nbaTitles = nbaTitles;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Double getLuxuryTax() {
        return luxuryTax;
    }

    public void setLuxuryTax(Double luxuryTax) {
        this.luxuryTax = luxuryTax;
    }

    public Integer getTeamOvr() {
        return teamOvr;
    }

    public void setTeamOvr(Integer teamOvr) {
        this.teamOvr = teamOvr;
    }

    public Integer getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(Integer playerCount) {
        this.playerCount = playerCount;
    }

    public Double getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(Double averageAge) {
        this.averageAge = averageAge;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
