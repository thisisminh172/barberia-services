package com.barberia.app.controllers;

import com.barberia.app.models.Salon;
import com.barberia.app.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class SalonController {
    @Autowired
    private SalonService salonService;

    // GET ALL SALONS
    @GetMapping("salons")
    public List<Salon> getAllSalons(){
        return salonService.findAll();
    }

    // CREATE SALON
    @PostMapping("salons")
    public Salon createSalon(@RequestBody Salon salon){
        return salonService.save(salon);
    }

    //GET SALON BY ID
    @GetMapping("salons/{id}")
    public Optional<Salon> getSalonById(@PathVariable Long id){
        return salonService.findById(id);
    }

    //UPDATE SALON
    @PutMapping("salons/{id}")
    public Salon updateSalon(@PathVariable Long id, @RequestBody Salon salonDetails){
        Salon salon = salonService.findById(id).get();
        salon.setSalonName(salonDetails.getSalonName());
        salon.setAddress(salonDetails.getAddress());
        salon.setEmail(salonDetails.getEmail());
        salon.setThumbnailUrl(salonDetails.getThumbnailUrl());
        salon.setPhoneNumber(salonDetails.getPhoneNumber());
        salon.setOpenedHour(salonDetails.getOpenedHour());
        salon.setClosedHour(salonDetails.getClosedHour());
        salon.setMinuteInOneTimeSlot(salonDetails.getMinuteInOneTimeSlot());
        salon.setAvailableForOnlineBooking(salonDetails.isAvailableForOnlineBooking());
        salon.setNumberOfTurnInOneTimeSlot(salonDetails.getNumberOfTurnInOneTimeSlot());
        salon.setOpened(salonDetails.isOpened());
        salon.setCarParkingAvailable(salonDetails.isCarParkingAvailable());
        return salonService.save(salon);
    }

    //DELETE SALON
    @DeleteMapping("salons/{id}")
    public ResponseEntity<Salon> deleteSalon(@PathVariable Long id){
        Salon salon = salonService.findById(id).get();
        salon.setOpened(false);
        salonService.save(salon);
        return ResponseEntity.ok(salon);
    }
}
