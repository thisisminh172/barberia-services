package com.barberia.app.services;

import com.barberia.app.models.Booking;
import com.barberia.app.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    // GET ALL BOOKINGs
    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    // GET BOOKING BY ID
    public Optional<Booking> findById(Long id){
        return bookingRepository.findById(id);
    }

    // DETELE BOOKING
    public void delete(Long id){
        bookingRepository.deleteById(id);
    }

    //UPDATE BOOKING
    public void save(Booking booking){
        bookingRepository.save(booking);
    }
}
