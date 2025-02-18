package com.example.cricket.repository;

import com.example.cricket.entity.Inning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InningRepository extends JpaRepository<Inning, Long> {
    List<Inning> findByGameGameId(Long gameId);
    List<Inning> findByTeamTeamId(Long teamId);
}
