package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Turn;
import com.barberia.app.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class TurnController {
    @Autowired
    private TurnService turnService;

    // GET ALL TURNS
    @GetMapping("turns")
    public List<Turn> findAll(){
        return turnService.findAll();
    }
    // GET TURN BY ID
    @GetMapping("turns/{id}")
    public Optional<Turn> findById(@PathVariable Long id){
        return turnService.findById(id);
    }
    // CREATE TURN
    @PostMapping("turns")
    public Turn createTurn(@RequestBody Turn turn){
        return turnService.save(turn);
    }
    // UPDATE TURN
    @PutMapping("turns/{id}")
    public Turn updateTurn(@PathVariable Long id, @RequestBody Turn turnUpdate){
        Turn turn = turnService.findById(id).get();
        turn.setEmployee(turnUpdate.getEmployee());
        turn.setBooking(turnUpdate.getBooking());
        return turnService.save(turn);
    }
    // DELETE TURN
    @DeleteMapping("turns/{id}")
    public void delete(@PathVariable Long id){
        turnService.delete(id);
    }
}
