package com.nba.nba_zone;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    private String position;
    @Column(name = "secondary_position")
    private String secondaryPosition;
    @Column(name = "birth_year")
    private Integer birthYear;
    private Integer rating;
    @Column(name = "new_rating")
    private Integer newRating;
    private String nationality;

    // Kontrakty na poszczególne lata
    @Column(name = "contract_25_26")
    private Double contract2526;
    @Column(name = "contract_26_27")
    private Double contract2627;
    @Column(name = "contract_27_28")
    private Double contract2728;
    @Column(name = "contract_28_29")
    private Double contract2829;
    @Column(name = "contract_29_30")
    private Double contract2930;

    @Column(name = "new_player")
    private boolean newPlayer;

    @Column(name = "rookie")
    private boolean rookie;

    // Opcja kontraktu
    @Enumerated(EnumType.STRING)
    private ContractOption option2526;
    @Enumerated(EnumType.STRING)
    private ContractOption option2627;
    @Enumerated(EnumType.STRING)
    private ContractOption option2728;

    @Enumerated(EnumType.STRING)
    private ContractOption option2829;

    @Enumerated(EnumType.STRING)
    private ContractOption option2930;

    public Player() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSecondaryPosition() {
        return secondaryPosition;
    }

    public void setSecondaryPosition(String secondaryPosition) {
        this.secondaryPosition = secondaryPosition;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getAge() {
        if (this.birthYear == null) return 0;
        int currentYear = 2026;
        return currentYear - this.birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getNewRating() {
        return newRating;
    }

    public void setNewRating(Integer newRating) {
        this.newRating = newRating;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isNewPlayer() {
        return newPlayer;
    }

    public void setNewPlayer(boolean newPlayer) {
        this.newPlayer = newPlayer;
    }

    public boolean isRookie() {
        return rookie;
    }

    public void setRookie(boolean rookie) {
        this.rookie = rookie;
    }

    public ContractOption getOption2526() {
        return option2526;
    }

    public void setOption2526(ContractOption option2526) {
        this.option2526 = option2526;
    }

    public ContractOption getOption2627() {
        return option2627;
    }

    public Double getCurrentSalary() {
        if (this.contract2526 == null) return 0.0;
        return this.contract2526 / 1_000_000.0;
    }
    public void setOption2627(ContractOption option2627) {
        this.option2627 = option2627;
    }

    public Double getContract2526() {
        return contract2526;
    }

    public void setContract2526(Double contract2526) {
        this.contract2526 = contract2526;
    }

    public Double getContract2627() {
        return contract2627;
    }

    public void setContract2627(Double contract2627) {
        this.contract2627 = contract2627;
    }

    public Double getContract2728() {
        return contract2728;
    }

    public void setContract2728(Double contract2728) {
        this.contract2728 = contract2728;
    }

    public Double getContract2829() {
        return contract2829;
    }

    public void setContract2829(Double contract2829) {
        this.contract2829 = contract2829;
    }

    public Double getContract2930() {
        return contract2930;
    }

    public void setContract2930(Double contract2930) {
        this.contract2930 = contract2930;
    }

    public ContractOption getOption2728() {
        return option2728;
    }

    public void setOption2728(ContractOption option2728) {
        this.option2728 = option2728;
    }

    public ContractOption getOption2829() {
        return option2829;
    }

    public void setOption2829(ContractOption option2829) {
        this.option2829 = option2829;
    }

    public ContractOption getOption2930() {
        return option2930;
    }

    public void setOption2930(ContractOption option2930) {
        this.option2930 = option2930;
    }
}