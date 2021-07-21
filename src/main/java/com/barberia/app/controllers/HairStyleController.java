package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.HairStyle;
import com.barberia.app.services.HairStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class HairStyleController {
    @Autowired
    private HairStyleService hairStyleService;

    // GET ALL HAIRSTYLE
    @GetMapping("hairstyles")
    public List<HairStyle> findAll(){
        return hairStyleService.findAll();
    }
    // GET HAIR STYLE BY ID
    @GetMapping("hairstyles/{id}")
    public Optional<HairStyle> findById(@PathVariable Long id){
        return hairStyleService.findById(id);
    }
    // CREATE HAIRSTYLE
    @PostMapping("hairstyles")
    public HairStyle createHairStyle(@RequestBody HairStyle hairStyle){
        return hairStyleService.save(hairStyle);
    }
    // UPDATE HAIRSTYLE
    @PutMapping("hairstyles/{id}")
    public HairStyle updateHairStyle(@PathVariable Long id, @RequestBody HairStyle hairStyleUpdate){
        HairStyle hairStyle = hairStyleService.findById(id).get();
        hairStyle.setTitle(hairStyleUpdate.getTitle());
        hairStyle.setDescription(hairStyleUpdate.getDescription());
        return hairStyleService.save(hairStyle);
    }
    // DELETE HAIRSTYLE
    @DeleteMapping("hairstyles/{id}")
    public void delete(@PathVariable Long id){
        hairStyleService.delete(id);
    }
}
