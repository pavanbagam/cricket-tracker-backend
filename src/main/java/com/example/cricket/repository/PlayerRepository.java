package com.example.cricket.repository;
import java.util.List;

import com.example.cricket.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByFNameAndLName(String fName, String lName);
    List<Player> findAllByFNameAndLName(String fName, String lName);

}
