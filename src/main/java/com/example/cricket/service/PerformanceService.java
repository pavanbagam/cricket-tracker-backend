package com.example.cricket.service;

import com.example.cricket.entity.Performance;
import com.example.cricket.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }

    public Performance getPerformanceByPlayerId(Long playerId) {
        return performanceRepository.findByPlayerPlayerId(playerId);
    }

    public Performance savePerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    public Performance updatePerformance(Long id, Performance updatedPerformance) {
        Optional<Performance> existingPerformance = performanceRepository.findById(id);
        if (existingPerformance.isPresent()) {
            Performance performance = existingPerformance.get();
            performance.setGamesPlayed(updatedPerformance.getGamesPlayed());
            performance.setInningsPlayed(updatedPerformance.getInningsPlayed());
            performance.setRunsScored(updatedPerformance.getRunsScored());
            performance.setDeliveriesFaced(updatedPerformance.getDeliveriesFaced());
            performance.setFours(updatedPerformance.getFours());
            performance.setSixes(updatedPerformance.getSixes());
            performance.setBattingStrikeRate(updatedPerformance.getBattingStrikeRate());
            performance.setBattingAverage(updatedPerformance.getBattingAverage());
            performance.setNotOuts(updatedPerformance.getNotOuts());
            performance.setDeliveriesBowled(updatedPerformance.getDeliveriesBowled());
            performance.setRunsGiven(updatedPerformance.getRunsGiven());
            performance.setBowlingStrikeRate(updatedPerformance.getBowlingStrikeRate());
            performance.setBowlingAverage(updatedPerformance.getBowlingAverage());
            performance.setWicketsTaken(updatedPerformance.getWicketsTaken());
            return performanceRepository.save(performance);
        } else {
            return null;
        }
    }

    public void deletePerformance(Long id) {
        performanceRepository.deleteById(id);
    }
}
