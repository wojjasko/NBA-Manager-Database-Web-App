package com.nba.nba_zone;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TradeService {
    @Autowired private PlayerRepository playerRepository;
    @Autowired private TeamRepository teamRepository;
    @Autowired private TradeRepository tradeRepository;

    public List<Trade> getAllTradesDesc(Integer tier) {
        List<Trade> trades = (tier != null)
                ? tradeRepository.findByTierOrderByDateDescIdDesc(tier) // nowa nazwa
                : tradeRepository.findAllByOrderByDateDescIdDesc();     // nowa nazwa

        for (Trade t : trades) {
            t.getItems().removeIf(item -> item.getPlayer() == null && item.getPickDescription() == null);
        }
        return trades;
    }

    @Transactional
    public void deleteTrade(Long tradeId) {
        tradeRepository.deleteById(tradeId);
    }

    @Transactional
    public void processTrade(Map<String, String> allParams,
                             List<Long> playersFromA,
                             List<Long> playersFromB,
                             List<Long> playersFromC) {
        Trade trade = new Trade();
        trade.setTier(Integer.parseInt(allParams.getOrDefault("tier", "4")));
        trade.setDate(java.time.LocalDate.now());

        Map<String, Team> teamMap = new HashMap<>();
        // Ważne: to mapowanie musi być identyczne z literkami w HTML
        if (allParams.get("teamAId") != null && !allParams.get("teamAId").isEmpty())
            teamMap.put("A", teamRepository.findById(Long.parseLong(allParams.get("teamAId"))).orElse(null));
        if (allParams.get("teamBId") != null && !allParams.get("teamBId").isEmpty())
            teamMap.put("B", teamRepository.findById(Long.parseLong(allParams.get("teamBId"))).orElse(null));
        if (allParams.get("teamCId") != null && !allParams.get("teamCId").isEmpty())
            teamMap.put("C", teamRepository.findById(Long.parseLong(allParams.get("teamCId"))).orElse(null));

        // Przetwarzamy graczy z każdej strony osobno
        processPlayers(playersFromA, "A", teamMap, allParams, trade);
        processPlayers(playersFromB, "B", teamMap, allParams, trade);
        processPlayers(playersFromC, "C", teamMap, allParams, trade);

        // Przetwarzamy picki
        processAssets("A", teamMap, allParams, trade);
        processAssets("B", teamMap, allParams, trade);
        processAssets("C", teamMap, allParams, trade);

        tradeRepository.save(trade);
    }

    private void processPlayers(List<Long> ids, String fromSide, Map<String, Team> teamMap, Map<String, String> params, Trade trade) {
        if (ids == null || ids.isEmpty()) return;

        Team fromTeam = teamMap.get(fromSide);
        if (fromTeam == null) return;

        for (Long id : ids) {
            Player p = playerRepository.findById(id).orElse(null);
            if (p == null) continue;

            String targetSideLetter = params.get("targetFor" + id);
            Team toTeam = teamMap.get(targetSideLetter);

            if (toTeam == null || toTeam.getId().equals(fromTeam.getId())) {
                // Szukamy jakiejkolwiek innej drużyny w wymianie
                for (Map.Entry<String, Team> entry : teamMap.entrySet()) {
                    if (!entry.getKey().equals(fromSide) && entry.getValue() != null) {
                        toTeam = entry.getValue();
                        break;
                    }
                }
            }

            if (toTeam != null) {
                // 1. Zmiana fizyczna w bazie
                p.setTeam(toTeam);
                playerRepository.save(p);

                // 2. Dodanie do historii transakcji
                TradeItem item = new TradeItem();
                item.setTrade(trade);
                item.setPlayer(p);
                item.setFromTeam(fromTeam);
                item.setToTeam(toTeam);
                trade.getItems().add(item);
            }
        }
    }

    private void processAssets(String fromSide, Map<String, Team> teamMap, Map<String, String> params, Trade trade) {
        params.forEach((key, value) -> {
            if (key.startsWith("picksFrom" + fromSide) && value != null && !value.isEmpty()) {
                String suffix = key.contains("_") ? key.substring(key.indexOf("_") + 1) : "0";
                Team toTeam = teamMap.get(params.get("targetForPick" + fromSide + "_" + suffix));
                Team fromTeam = teamMap.get(fromSide);
                if (toTeam != null && fromTeam != null) {
                    TradeItem item = new TradeItem();
                    item.setTrade(trade);
                    item.setPickDescription(value);
                    item.setFromTeam(fromTeam);
                    item.setToTeam(toTeam);
                    trade.getItems().add(item);
                }
            }
        });
    }
}