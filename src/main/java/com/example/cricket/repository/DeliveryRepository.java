package com.example.cricket.repository;

import com.example.cricket.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByInningInningId(Long inningId);
    List<Delivery> findByBowlerPlayerId(Long bowlerId);
    List<Delivery> findByBatsmanPlayerId(Long batsmanId);
}
