package com.nba.nba_zone;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model; // Tego brakowało dla Model

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade-machine")
public class TradeController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private TradeRepository tradeRepository;

    @GetMapping
    public String showMachine(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "trade-machine";
    }

    @PostMapping("/execute")
    public String execute(@RequestParam Map<String, String> allParams,
                          @RequestParam(required = false) List<Long> playersFromA,
                          @RequestParam(required = false) List<Long> playersFromB,
                          @RequestParam(required = false) List<Long> playersFromC) {
        tradeService.processTrade(allParams, playersFromA, playersFromB, playersFromC);
        return "redirect:/trade-machine/transfers";
    }

    @GetMapping("/transfers")
    public String showTransfers(@RequestParam(required = false) Integer tier, Model model) {
        model.addAttribute("trades", tradeService.getAllTradesDesc(tier));
        return "transfers";
    }

    @PostMapping("/transfers/delete/{id}") // Razem z @RequestMapping("/trade-machine") na gorze klasy da to /trade-machine/transfers/delete/{id}
    public String deleteTrade(@PathVariable Long id) {
        tradeService.deleteTrade(id);
        return "redirect:/trade-machine/transfers";
    }
}