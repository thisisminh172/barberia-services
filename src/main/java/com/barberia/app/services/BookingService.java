package com.barberia.app.services;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Salon;
import com.barberia.app.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SalonService salonService;

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
    public Booking save(Booking booking){
        return bookingRepository.save(booking);
    }

    public List<Booking> findOnlineBooking(){
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return bookingRepository.findByStatusAndChosenTimeSlotAfter("online",yesterday);
    }

    public List<Booking> findOnlineBookingAfterSpecificTime(){
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return bookingRepository.findAllByStatusAndChosenTimeSlotAfterOrderByChosenTimeSlotAsc("online",yesterday);
    }

    public List<Booking> findCheckInBookingAfterSpecificTime(){
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return bookingRepository.findAllByStatusAndChosenTimeSlotAfterOrderByChosenTimeSlotAsc("check-in",yesterday);
    }

    public List<Booking> findByCustomerId(long customerId){
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return bookingRepository.findByCustomerIdAndChosenTimeSlotAfter(customerId,yesterday);
    }



}
