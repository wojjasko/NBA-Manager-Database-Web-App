package com.nba.nba_zone;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam(Team team);

}