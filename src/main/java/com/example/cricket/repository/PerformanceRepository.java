package com.example.cricket.repository;

import com.example.cricket.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    Performance findByPlayerPlayerId(Long playerId);
}
