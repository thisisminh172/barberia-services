package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    // GET ALL BOOKING
    @GetMapping("bookings")
    public List<Booking> findAll(){
        return bookingService.findAll();
    }
    // GET BOOKING BY ID
    @GetMapping("bookings/{id}")
    public Optional<Booking> findById(@PathVariable Long id){
        return bookingService.findById(id);
    }
    // CREATE BOOKING
    @PostMapping("bookings")
    public Booking createBooking(@RequestBody Booking booking){
        return bookingService.save(booking);
    }
    // UPDATE BOOKING
    @PutMapping("bookings/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking bookingUpdate){
        Booking booking = bookingService.findById(id).get();
        booking.setChosenTimeSlot(bookingUpdate.getChosenTimeSlot());
        booking.setDescription(bookingUpdate.getDescription());
        booking.setOnlineBooking(bookingUpdate.isOnlineBooking());
        booking.setStatus(bookingUpdate.getStatus());
        booking.setEmployee(bookingUpdate.getEmployee());
        booking.setSalon(bookingUpdate.getSalon());
        return bookingService.save(booking);
    }
    // DELETE BOOKING
    @DeleteMapping("bookings/{id}")
    public void delete(@PathVariable Long id){
        bookingService.delete(id);
    }
}
