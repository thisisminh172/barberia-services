package com.barberia.app.clientControllers;

import com.barberia.app.models.Feedback;
import com.barberia.app.services.FeedbackService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientFeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping("/contact")
    public String showContact(Model model) {
        boolean message = false;
        model.addAttribute("message", message);
        return "client/contact";
    }

    @PostMapping("/contact-send")
    public String sendFeedback(@RequestParam(value = "fullName") String fullName, @RequestParam(value = "email") String email, @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "comment") String comment, Model model){
        Feedback feedback = new Feedback();
        feedback.setSend(false);
        feedback.setEmail(email);
        feedback.setComment(comment);
        feedback.setFullName(fullName);
        feedback.setPhoneNumber(phoneNumber);
        feedbackService.save(feedback);
        model.addAttribute("message", true);
        return "client/contact";
    }
}
