package com.nba.nba_zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/teams")
    public String listTeams(Model model) {
        model.addAttribute("eastTeams", teamRepository.findByConference("Eastern"));
        model.addAttribute("westTeams", teamRepository.findByConference("Western"));
        model.addAttribute("eastDivisions", List.of("Atlantic", "Central", "Southeast"));
        model.addAttribute("westDivisions", List.of("Northwest", "Pacific", "Southwest"));
        return "teams-list";
    }

    @GetMapping("/teams/{id}")
    public String getTeamDetails(@PathVariable Long id, Model model) {
        Team team = teamRepository.findById(id).orElseThrow();
        List<Player> players = playerRepository.findByTeam(team);

        // 1. Sortowanie pozycjami
        List<String> posOrder = Arrays.asList("PG", "SG", "SF", "PF", "C");
        players.sort(Comparator.comparingInt((Player p) -> {
            int index = posOrder.indexOf(p.getPosition());
            return index == -1 ? 99 : index;
        }).thenComparing(Player::getRating, Comparator.reverseOrder()));

        // 2. Bezpieczne sumowanie roczne
        long s2526 = (long) players.stream().mapToDouble(p -> p.getContract2526() != null ? p.getContract2526() : 0).sum();
        long s2627 = (long) players.stream().mapToDouble(p -> p.getContract2627() != null ? p.getContract2627() : 0).sum();
        long s2728 = (long) players.stream().mapToDouble(p -> p.getContract2728() != null ? p.getContract2728() : 0).sum();
        long s2829 = (long) players.stream().mapToDouble(p -> p.getContract2829() != null ? p.getContract2829() : 0).sum();
        long s2930 = (long) players.stream().mapToDouble(p -> p.getContract2930() != null ? p.getContract2930() : 0).sum();

        // 3. Statystyki ogólne
        int avgOvr = players.isEmpty() ? 0 : (int) players.stream().mapToInt(Player::getRating).average().orElse(0);
        double avgAge = players.isEmpty() ? 0 : players.stream().mapToDouble(Player::getAge).average().orElse(0);

        team.setTotalSalary((double) s2526);
        team.setTeamOvr(avgOvr);
        team.setAverageAge(avgAge);
        team.setPlayerCount(players.size());

        model.addAttribute("team", team);
        model.addAttribute("players", players);
        model.addAttribute("sums", List.of(s2526, s2627, s2728, s2829, s2930));

        return "team-details";
    }
}