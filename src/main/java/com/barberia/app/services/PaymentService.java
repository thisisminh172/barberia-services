package com.barberia.app.services;

import com.barberia.app.models.Payment;
import com.barberia.app.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // GET ALL PAYMENTS
    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }

    // GET PAYMENT BY ID
    public Optional<Payment> findById(Long id){
        return paymentRepository.findById(id);
    }

    // DELETE
    public void delete(Long id){
        paymentRepository.deleteById(id);
    }

    // UPDATE AND ADD
    public void save(Payment payment){
        paymentRepository.save(payment);
    }
}
