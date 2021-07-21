package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.BookingDetail;
import com.barberia.app.services.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class BookingDetailController {
    @Autowired
    private BookingDetailService bookingDetailService;

    // GET ALL BOOKINGDETAIL
    @GetMapping("bookingdetails")
    public List<BookingDetail> findAll(){
        return bookingDetailService.findAll();
    }
    // GET BOOKINGDETAIL BY ID
    @GetMapping("bookingdetails/{id}")
    public Optional<BookingDetail> findById(@PathVariable Long id){
        return bookingDetailService.findById(id);
    }
    // CREATE BOOKINGDETAIL
    @PostMapping("bookingdetails")
    public BookingDetail createBookingDetail(@RequestBody BookingDetail bookingDetail){
        return bookingDetailService.save(bookingDetail);
    }
    // UPDATE BOOKINGDETAIL
    @PutMapping("bookingdetails/{id}")
    public BookingDetail updateBookingDetail(@PathVariable Long id, @RequestBody BookingDetail bookingDetailUpdate){
        BookingDetail bookingDetail = bookingDetailService.findById(id).get();
        bookingDetail.setService(bookingDetailUpdate.getService());
        return bookingDetailService.save(bookingDetail);
    }
    // DELETE BOOKINGDETAIL
    @DeleteMapping("bookingdetails/{id}")
    public void delete(@PathVariable Long id){
        bookingDetailService.delete(id);
    }
}
