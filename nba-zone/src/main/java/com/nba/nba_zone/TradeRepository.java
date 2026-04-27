package com.nba.nba_zone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findAllByOrderByDateDescIdDesc();
    List<Trade> findByTierOrderByDateDescIdDesc(Integer tier);
}