package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Feedback;
import com.barberia.app.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    // GET ALL FEEDBACK
    @GetMapping("feedbacks")
    public List<Feedback> findAll(){
        return feedbackService.findAll();
    }
    // GET FEEDBACK BY ID
    @GetMapping("feedbacks/{id}")
    public Optional<Feedback> findById(@PathVariable Long id){
        return feedbackService.findById(id);
    }
    // CREATE FEEDBACK
    @PostMapping("feedbacks")
    public Feedback createFeedback(@RequestBody Feedback feedback){
        return feedbackService.save(feedback);
    }
    // UPDATE FEEDBACK
    @PutMapping("feedbacks/{id}")
    public Feedback updateFeedback(@PathVariable Long id, @RequestBody Feedback feedbackUpdate){
        Feedback feedback = feedbackService.findById(id).get();
        // only fix comment contents
        feedback.setComment(feedbackUpdate.getComment());
        return feedbackService.save(feedback);
    }
    // DELETE FEEDBACK
    @DeleteMapping("feedbacks/{id}")
    public void delete(@PathVariable Long id){
        feedbackService.delete(id);
    }
}
