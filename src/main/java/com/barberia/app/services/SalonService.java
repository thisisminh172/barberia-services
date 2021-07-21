package com.barberia.app.services;

import com.barberia.app.models.Salon;
import com.barberia.app.repositories.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalonService {

    @Autowired
    private SalonRepository salonRepository;

    // GET ALL SALONS
    public List<Salon> findAll(){
        return salonRepository.findAll();
    }

    // GET SALON BY ID
    public Optional<Salon> findById(Long id){
        return salonRepository.findById(id);
    }

    // DELETE
    public void delete(Long id){
        salonRepository.deleteById(id);
    }

    // UPDATE AND ADD
    public Salon save(Salon salon){
        return salonRepository.save(salon);
    }
}
