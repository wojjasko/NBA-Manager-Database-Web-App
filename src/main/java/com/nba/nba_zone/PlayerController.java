package com.nba.nba_zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/players")
    public String showPlayers(Model model) {
        // Pobieramy wszystkich graczy z bazy i wysyłamy do HTML
        model.addAttribute("nbaPlayers", playerRepository.findAll());
        return "players-list";
    }

    @GetMapping("/add-player")
    public String showAddForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("options", ContractOption.values());
        // Pobieramy wszystkie drużyny i sortujemy je alfabetycznie dla wygody
        model.addAttribute("allTeams", teamRepository.findAll());
        return "add-player";
    }

    @PostMapping("/save-player")
    public String savePlayer(@ModelAttribute("player") Player player) {
        playerRepository.save(player);
        return "redirect:/players"; // Po zapisaniu wracamy do tabeli
    }
}