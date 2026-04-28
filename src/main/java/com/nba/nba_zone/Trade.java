package com.nba.nba_zone;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private int tier; // 1 - Gold (Blockbuster), 2, 3, 4 - Minor

    @OneToMany(mappedBy = "trade", cascade = CascadeType.ALL)
    private List<TradeItem> items = new ArrayList<>();

    public Trade() { this.date = LocalDate.now(); }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public List<TradeItem> getItems() { return items; }
    public void setItems(List<TradeItem> items) { this.items = items; }

    public int getTier() { return tier; }
    public void setTier(int tier) { this.tier = tier; }

    public List<Team> getInvolvedTeams() {
        List<Team> teams = new ArrayList<>();
        for (TradeItem item : items) {
            if (!teams.contains(item.getToTeam())) {
                teams.add(item.getToTeam());
            }
            if (!teams.contains(item.getFromTeam())) {
                teams.add(item.getFromTeam());
            }
        }
        return teams;
    }
    public double getIncomingSalaryForTeam(Long teamId) {
        return items.stream()
                .filter(item -> item.getToTeam() != null && item.getToTeam().getId().equals(teamId))
                .filter(item -> item.getPlayer() != null)
                .mapToDouble(item -> item.getPlayer().getCurrentSalary())
                .sum();
    }
    public double getOutgoingSalaryForTeam(Long teamId) {
        return items.stream()
                .filter(item -> item.getFromTeam() != null && item.getFromTeam().getId().equals(teamId))
                .filter(item -> item.getPlayer() != null)
                .mapToDouble(item -> item.getPlayer().getCurrentSalary())
                .sum();
    }
}