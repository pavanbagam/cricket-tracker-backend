package com.example.cricket.service;

import com.example.cricket.entity.Delivery;
import com.example.cricket.entity.Inning;
import com.example.cricket.entity.Performance;
import com.example.cricket.entity.Player;
import com.example.cricket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private InningRepository inningRepository;



    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    public List<Delivery> getDeliveriesByInning(Long inningId) {
        return deliveryRepository.findByInningInningId(inningId);
    }

    public List<Delivery> getDeliveriesByBowler(Long bowlerId) {
        return deliveryRepository.findByBowlerPlayerId(bowlerId);
    }

    public List<Delivery> getDeliveriesByBatsman(Long batsmanId) {
        return deliveryRepository.findByBatsmanPlayerId(batsmanId);
    }


    public Delivery saveDelivery(Delivery delivery) {
        // Ensure Inning Exists
        Optional<Inning> inning = inningRepository.findById(delivery.getInning().getInningId());
        if (inning.isEmpty()) {
            throw new RuntimeException("Inning with ID " + delivery.getInning().getInningId() + " not found.");
        }
        delivery.setInning(inning.get());

        // Ensure Bowler Exists
        Optional<Player> bowler = playerRepository.findById(delivery.getBowler().getPlayerId());
        if (bowler.isEmpty()) {
            throw new RuntimeException("Bowler with ID " + delivery.getBowler().getPlayerId() + " not found.");
        }
        delivery.setBowler(bowler.get());

        // Ensure Batsman Exists
        Optional<Player> batsman = playerRepository.findById(delivery.getBatsman().getPlayerId());
        if (batsman.isEmpty()) {
            throw new RuntimeException("Batsman with ID " + delivery.getBatsman().getPlayerId() + " not found.");
        }
        delivery.setBatsman(batsman.get());

        // Ensure Non-Striker Exists (If Provided)
        if (delivery.getNonStriker() != null) {
            Optional<Player> nonStriker = playerRepository.findById(delivery.getNonStriker().getPlayerId());
            if (nonStriker.isEmpty()) {
                throw new RuntimeException("Non-striker with ID " + delivery.getNonStriker().getPlayerId() + " not found.");
            }
            delivery.setNonStriker(nonStriker.get());
        }

        // Ensure Fielder Exists (If Provided)
        if (delivery.getFielder() != null) {
            Optional<Player> fielder = playerRepository.findById(delivery.getFielder().getPlayerId());
            if (fielder.isEmpty()) {
                throw new RuntimeException("Fielder with ID " + delivery.getFielder().getPlayerId() + " not found.");
            }
            delivery.setFielder(fielder.get());
        }
        Delivery savedDelivery = deliveryRepository.save(delivery);
        updateInning(savedDelivery);
        updatePlayerPerformance(savedDelivery);
        return savedDelivery;
    }

    private void updateInning(Delivery delivery) {
        Inning inning = delivery.getInning();
        if(delivery.isWicket())
        {
            inning.setWickets(inning.getWickets() + 1);
        }
        inning.setTotalRuns(inning.getTotalRuns() + delivery.getRunsScored());
        if(!delivery.isWideOrNoBall())
        {
            if(inning.getDeliveries() == 5)
            {
                inning.setDeliveries(0);
                inning.setOvers(inning.getOvers() + 1);
            }
            else
            {
                inning.setDeliveries(inning.getDeliveries() + 1);
            }
        }

        if(delivery.isWideOrNoBall())
        {
            inning.setTotalRuns(inning.getTotalRuns() + 1);
        }
        inningRepository.save(inning);
    }

    private void updatePlayerPerformance(Delivery delivery) {
        updateBatsmanPerformance(delivery);
        updateBowlerPerformance(delivery);
    }

    private void updateBatsmanPerformance(Delivery delivery) {
        Player batsman = delivery.getBatsman();
        Performance performance = performanceRepository.findByPlayerPlayerId(batsman.getPlayerId());

        performance.setRunsScored(performance.getRunsScored() + delivery.getRunsScored());
        performance.setDeliveriesFaced(performance.getDeliveriesFaced() + 1);

        if (delivery.getRunsScored() == 4) {
            performance.setFours(performance.getFours() + 1);
        }
        if (delivery.getRunsScored() == 6) {
            performance.setSixes(performance.getSixes() + 1);
        }

        // Update batting average and strike rate
        performance.setBattingStrikeRate((double) performance.getRunsScored() / performance.getDeliveriesFaced() * 100);
        if (performance.getInningsPlayed() - performance.getNotOuts() > 0) {
            performance.setBattingAverage((double) performance.getRunsScored() / (performance.getInningsPlayed() - performance.getNotOuts()));
        }

        performanceRepository.save(performance);
    }

    private void updateBowlerPerformance(Delivery delivery) {
        Player bowler = delivery.getBowler();
        Performance performance = performanceRepository.findByPlayerPlayerId(bowler.getPlayerId());

        performance.setDeliveriesBowled(performance.getDeliveriesBowled() + 1);
        performance.setRunsGiven(performance.getRunsGiven() + delivery.getRunsScored());

        if (delivery.isWicket()) {
            performance.setWicketsTaken(performance.getWicketsTaken() + 1);
        }

        // Update bowling strike rate and average
        if (performance.getWicketsTaken() > 0) {
            performance.setBowlingStrikeRate((double) performance.getDeliveriesBowled() / performance.getWicketsTaken());
            performance.setBowlingAverage((double) performance.getRunsGiven() / performance.getWicketsTaken());
        }

        performanceRepository.save(performance);
    }

    public Delivery updateDelivery(Long id, Delivery updatedDelivery) {
        Optional<Delivery> existingDelivery = deliveryRepository.findById(id);
        if (existingDelivery.isPresent()) {
            Delivery delivery = existingDelivery.get();
            delivery.setInning(updatedDelivery.getInning());
            delivery.setBowler(updatedDelivery.getBowler());
            delivery.setBatsman(updatedDelivery.getBatsman());
            delivery.setNonStriker(updatedDelivery.getNonStriker());
            delivery.setFielder(updatedDelivery.getFielder());
            delivery.setRunsScored(updatedDelivery.getRunsScored());
            delivery.setWicket(updatedDelivery.isWicket());
            delivery.setDismissalType(updatedDelivery.getDismissalType());
            delivery.setBallNumber(updatedDelivery.getBallNumber());
            delivery.setOverNumber(updatedDelivery.getOverNumber());
            return deliveryRepository.save(delivery);
        } else {
            return null;
        }
    }

    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
