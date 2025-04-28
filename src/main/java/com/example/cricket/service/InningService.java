package com.example.cricket.service;

import com.example.cricket.entity.Inning;
import com.example.cricket.repository.InningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cricket.dto.PlayerMiniDTO;
import com.example.cricket.repository.SquadRepository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import com.example.cricket.entity.Squad;
import com.example.cricket.dto.InningMiniDTO;



@Service
@RequiredArgsConstructor
public class InningService {

    @Autowired
    private InningRepository inningRepository;

    @Autowired
    private SquadRepository squadRepository;

    public List<Inning> getAllInnings() {
        return inningRepository.findAll();
    }

    public Optional<Inning> getInningById(Long id) {
        return inningRepository.findById(id);
    }

    public List<Inning> getInningsByGameId(Long gameId) {
        return inningRepository.findByGameGameId(gameId);
    }

    /* ---- mini helpers ---- */

    public List<InningMiniDTO> inningsMini(Long gameId){
        return getInningsByGameId(gameId).stream()
               .map(in -> new InningMiniDTO(
                        in.getInningId(),
                        in.getTeam().getTeamId(),
                        in.getTeam().getName()))
               .toList();
    }

    public List<PlayerMiniDTO> squadMiniByTeam(Long teamId){
        return squadRepository.squadMiniByTeam(teamId);           // delegate to projection query
    }

    public List<Inning> getInningsByTeamId(Long teamId) {
        return inningRepository.findByTeamTeamId(teamId);
    }

    public Inning saveInning(Inning inning) {
        return inningRepository.save(inning);
    }

    public Optional<Inning> updateInning(Long id, Inning updatedInning) {
        return inningRepository.findById(id).map(existingInning -> {
            existingInning.setTotalRuns(updatedInning.getTotalRuns());
            existingInning.setWickets(updatedInning.getWickets());
            existingInning.setOvers(updatedInning.getOvers());
            existingInning.setCurrOvers(updatedInning.getCurrOvers());
            existingInning.setDeliveries(updatedInning.getDeliveries());
            return inningRepository.save(existingInning);
        });
    }

    public void deleteInning(Long id) {
        inningRepository.deleteById(id);
    }
}