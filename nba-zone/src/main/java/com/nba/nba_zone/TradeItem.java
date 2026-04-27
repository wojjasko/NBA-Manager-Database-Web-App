package com.nba.nba_zone;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TradeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Trade trade;

    @ManyToOne
    private Player player; // Moze być null jesli to pick
    private String pickDescription; // Moze być null jesli to gracz

    @ManyToOne
    private Team fromTeam;
    @ManyToOne
    private Team toTeam;

    public TradeItem() {}
    // Gettery i Settery dla wszystkich pól...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Trade getTrade() { return trade; }
    public void setTrade(Trade trade) { this.trade = trade; }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public String getPickDescription() { return pickDescription; }
    public void setPickDescription(String pickDescription) { this.pickDescription = pickDescription; }
    public Team getFromTeam() { return fromTeam; }
    public void setFromTeam(Team fromTeam) { this.fromTeam = fromTeam; }
    public Team getToTeam() { return toTeam; }
    public void setToTeam(Team toTeam) { this.toTeam = toTeam; }

    public String getSafeSalaryDisplay() {
        if (this.player != null && this.player.getCurrentSalary() != null) {
            // Formatuje Double na string z jednym miejscem po przecinku
            return String.format("$%.1fM", this.player.getCurrentSalary());
        }
        return "";
    }

    public Double getSalaryValue() {
        if (this.player != null) {
            return this.player.getCurrentSalary();
        }
        return 0.0;
    }
}