package com.nba.nba_zone;

// POPRAWNY IMPORT:
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PlayoffController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/playoffs")
    public String showPlayoffs(Model model) {
        // Sortowanie: Najpierw Winy (Desc), potem BonusPointsy (Desc), potem Losses (Asc)
        List<Team> east = teamRepository.findByConferenceOrderByWinsDescBonusPointsDescLossesAsc("Eastern");
        List<Team> west = teamRepository.findByConferenceOrderByWinsDescBonusPointsDescLossesAsc("Western");

        model.addAttribute("east", east);
        model.addAttribute("west", west);

        return "playoffs";
    }

    @PostMapping("/playoffs/update")
    public String updateStandings(@RequestParam Map<String, String> allParams) {
        allParams.forEach((key, value) -> {
            try {
                Long id = Long.parseLong(key.split("_")[1]);
                Team team = teamRepository.findById(id).orElse(null);

                if (team != null && value != null && !value.isEmpty()) {
                    int val = Integer.parseInt(value);
                    if (key.startsWith("wins_")) team.setWins(val);
                    else if (key.startsWith("losses_")) team.setLosses(val);
                    else if (key.startsWith("bp_")) team.setBonusPoints(val); // Dodano zapis BP

                    teamRepository.save(team);
                }
            } catch (Exception e) { /* ignoruj błędy */ }
        });
        return "redirect:/playoffs";
    }
}