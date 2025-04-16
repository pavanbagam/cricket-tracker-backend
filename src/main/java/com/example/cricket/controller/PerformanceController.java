package com.example.cricket.controller;

import com.example.cricket.entity.Performance;
import com.example.cricket.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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

    // @GetMapping("/player")
    // public ResponseEntity<List<Performance>> getPerformanceByPlayerName(
    //         @RequestParam String fName,
    //         @RequestParam String lName) {

    //     List<Performance> records = performanceService.getPerformanceByPlayerName(fName, lName);
    //     if (records.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     } else {
    //         return ResponseEntity.ok(records);
    //     }
    // }

    @GetMapping("/search")
    public ResponseEntity<List<Performance>> getByPlayerName(
        @RequestParam String fName,
        @RequestParam String lName
    ) {
        List<Performance> performances = performanceService.getPerformancesByPlayerName(fName, lName);
        if (performances.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(performances);
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
