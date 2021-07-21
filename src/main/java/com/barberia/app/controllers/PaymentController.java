package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Payment;
import com.barberia.app.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    // GET ALL PAYMENTS
    @GetMapping("payments")
    public List<Payment> findAll(){
        return paymentService.findAll();
    }
    // GET PAYMENT BY ID
    @GetMapping("payments/{id}")
    public Optional<Payment> findById(@PathVariable Long id){
        return paymentService.findById(id);
    }
    // CREATE PAYMENT
    @PostMapping("payments")
    public Payment createPayment(@RequestBody Payment payment){
        return paymentService.save(payment);
    }
    // UPDATE PAYMENT
    @PutMapping("payments/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment paymentUpdate){
        Payment payment = paymentService.findById(id).get();
        payment.setTotalPrice(paymentUpdate.getTotalPrice());
        payment.setTurn(paymentUpdate.getTurn());
        payment.setPaymentMethod(paymentUpdate.getPaymentMethod());
        return paymentService.save(payment);
    }
    // DELETE PAYMENT
    @DeleteMapping("payments/{id}")
    public void delete(@PathVariable Long id){
        paymentService.delete(id);
    }
}
