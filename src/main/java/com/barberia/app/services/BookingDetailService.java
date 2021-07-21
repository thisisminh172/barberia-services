package com.barberia.app.services;

import com.barberia.app.models.BookingDetail;
import com.barberia.app.repositories.BookingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingDetailService {
    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    // GET ALL BOOKING DETAILS
    public List<BookingDetail> findAll(){
        return bookingDetailRepository.findAll();
    }

    // GET BOOKING DETAILS BY ID
    public Optional<BookingDetail> findById(Long id){
        return bookingDetailRepository.findById(id);
    }

    // DELETE BOOKING DETAILS BY ID
    public void delete(Long id){
        bookingDetailRepository.deleteById(id);
    }

    // UPDATE BOOKING DETAILS
    public BookingDetail save(BookingDetail bookingDetail){
        return bookingDetailRepository.save(bookingDetail);
    }

}
