package com.nba.nba_zone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeItemRepository extends JpaRepository<TradeItem, Long> {
}