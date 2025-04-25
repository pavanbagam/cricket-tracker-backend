package com.example.cricket.controller;

import com.example.cricket.entity.Inning;
import com.example.cricket.service.InningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.cricket.dto.InningMiniDTO;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/innings")
public class InningController {

    @Autowired
    private InningService inningService;

    @GetMapping
    public List<Inning> getAllInnings() {
        return inningService.getAllInnings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inning> getInningById(@PathVariable Long id) {
        return inningService.getInningById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    @GetMapping("/game/{gameId}")
    public List<Inning> getInningsByGameId(@PathVariable Long gameId) {
        return inningService.getInningsByGameId(gameId);
    }

    @GetMapping("/team/{teamId}")
    public List<Inning> getInningsByTeamId(@PathVariable Long teamId) {
        return inningService.getInningsByTeamId(teamId);
    }

    @PostMapping
    public Inning addInning(@RequestBody Inning inning) {
        return inningService.saveInning(inning);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inning> updateInning(@PathVariable Long id, @RequestBody Inning updatedInning) {
        return inningService.updateInning(id, updatedInning)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInning(@PathVariable Long id) {
        inningService.deleteInning(id);
        return ResponseEntity.ok("Inning deleted successfully!");
    }

    // InningController.java  (add at bottom)
    @GetMapping("/byGame/{gameId}/mini")
    public List<InningMiniDTO> inningsMini(@PathVariable Long gameId) {
        return inningService.getInningsByGameId(gameId)
                            .stream()
                            .map(in -> new InningMiniDTO(
                                    in.getInningId(),
                                    in.getTeam().getTeamId(),
                                    in.getTeam().getName()))
                            .toList();
    }

}