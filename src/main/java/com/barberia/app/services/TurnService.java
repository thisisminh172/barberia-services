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
    public void delete(Long id){
        turnRepository.deleteById(id);
    }

    // UPDATE AND ADD
    public Turn save(Turn turn){
        return turnRepository.save(turn);
    }

    public List<Turn> findAllNotYetPaymentTurn(){
        return turnRepository.findAllByStatus("notyet");
    }
    public List<Turn> findAllDonePaymentTurn(){
        return turnRepository.findAllByStatus("done");
    }
    public List<Turn> findAllByStatusAndEmployeeId(String status, long id){
        return turnRepository.findAllByStatusAndEmployeeId(status,id);
    }
}
