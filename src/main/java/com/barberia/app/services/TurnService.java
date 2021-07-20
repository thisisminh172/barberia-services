package com.barberia.app.services;

import com.barberia.app.models.Turn;
import com.barberia.app.repositories.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnService {

    @Autowired
    private TurnRepository turnRepository;

    // GET ALL TURNS
    public List<Turn> findAll(){
        return turnRepository.findAll();
    }

    // GET TURN BY ID
    public Optional<Turn> findById(Long id){
        return turnRepository.findById(id);
    }

    // DELETE
    public void detete(Long id){
        turnRepository.deleteById(id);
    }

    // UPDATE AND ADD
    public void save(Turn turn){
        turnRepository.save(turn);
    }
}
