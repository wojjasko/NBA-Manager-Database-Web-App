package com.nba.nba_zone;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByConference(String conference);
    List<Team> findByDivision(String division);

    List<Team> findByConferenceOrderByWinsDescBonusPointsDescLossesAsc(String conference);
}