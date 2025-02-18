package com.example.cricket.controller;

import com.example.cricket.entity.Performance;
import com.example.cricket.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performances")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping
    public List<Performance> getAllPerformances() {
        return performanceService.getAllPerformances();
    }

    @GetMapping("/{playerId}")
    public Performance getPerformanceByPlayerId(@PathVariable Long playerId) {
        return performanceService.getPerformanceByPlayerId(playerId);
    }

    @PostMapping
    public Performance savePerformance(@RequestBody Performance performance) {
        return performanceService.savePerformance(performance);
    }

    @PutMapping("/{id}")
    public Performance updatePerformance(@PathVariable Long id, @RequestBody Performance updatedPerformance) {
        return performanceService.updatePerformance(id, updatedPerformance);
    }

    @DeleteMapping("/{id}")
    public void deletePerformance(@PathVariable Long id) {
        performanceService.deletePerformance(id);
    }
}
