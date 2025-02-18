package com.example.cricket.controller;

import com.example.cricket.entity.Delivery;
import com.example.cricket.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;



    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @GetMapping("/inning/{inningId}")
    public List<Delivery> getDeliveriesByInning(@PathVariable Long inningId) {
        return deliveryService.getDeliveriesByInning(inningId);
    }

    @GetMapping("/bowler/{bowlerId}")
    public List<Delivery> getDeliveriesByBowler(@PathVariable Long bowlerId) {
        return deliveryService.getDeliveriesByBowler(bowlerId);
    }

    @GetMapping("/batsman/{batsmanId}")
    public List<Delivery> getDeliveriesByBatsman(@PathVariable Long batsmanId) {
        return deliveryService.getDeliveriesByBatsman(batsmanId);
    }

    //Done
    @PostMapping
    public Delivery saveDelivery(@RequestBody Delivery delivery) {
        return deliveryService.saveDelivery(delivery);
    }

    @PutMapping("/{id}")
    public Delivery updateDelivery(@PathVariable Long id, @RequestBody Delivery updatedDelivery) {
        return deliveryService.updateDelivery(id, updatedDelivery);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
}
