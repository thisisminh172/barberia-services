package com.barberia.app.services;

import com.barberia.app.models.HairStyle;
import com.barberia.app.repositories.HairStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HairStyleService {

    @Autowired
    private HairStyleRepository hairStyleRepository;

    // GET ALL HAIRSTYLES
    public List<HairStyle> findAll(){
        return hairStyleRepository.findAll();
    }

    // GET HAIRSTYLE BY ID
    public Optional<HairStyle> findById(Long id){
        return hairStyleRepository.findById(id);
    }

    // DELETE
    public void delete(Long id){
        hairStyleRepository.deleteById(id);
    }

    // UPDATE AND ADD
    public void save(HairStyle hairStyle){
        hairStyleRepository.save(hairStyle);
    }
}
