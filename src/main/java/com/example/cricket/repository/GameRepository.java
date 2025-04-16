package com.example.cricket.repository;

import com.example.cricket.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    
}
