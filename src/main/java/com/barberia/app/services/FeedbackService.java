package com.barberia.app.services;

import com.barberia.app.models.Feedback;
import com.barberia.app.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // GET ALL FEEDBACK
    public List<Feedback> findAll(){
        return feedbackRepository.findAll();
    }

    // GET FEEDBACK BY ID
    public Optional<Feedback> findById(Long id){
        return feedbackRepository.findById(id);
    }

    // DELETE FEEDBACK
    public void delete(Long id){
        feedbackRepository.deleteById(id);
    }

    // UPDATE FEEDBACK
    public void save(Feedback feedback){
        feedbackRepository.save(feedback);
    }
}
