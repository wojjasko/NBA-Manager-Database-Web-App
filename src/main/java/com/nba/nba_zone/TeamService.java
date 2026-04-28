package com.nba.nba_zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service // TEGO BRAKOWAŁO
public class TeamService {

    @Autowired // TEGO BRAKOWAŁO
    private TeamRepository teamRepository;

    public Map<String, List<Team>> getStandings() {
        List<Team> allTeams = teamRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Team::getWins).reversed()
                        .thenComparingInt(Team::getLosses))
                .collect(Collectors.toList());

        Map<String, List<Team>> conferenceMap = new HashMap<>();
        conferenceMap.put("East", allTeams.stream().filter(t -> t.getConference().equals("Eastern")).collect(Collectors.toList()));
        conferenceMap.put("West", allTeams.stream().filter(t -> t.getConference().equals("Western")).collect(Collectors.toList()));

        return conferenceMap;
    }
}