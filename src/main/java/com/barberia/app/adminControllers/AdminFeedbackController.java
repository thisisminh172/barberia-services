package com.barberia.app.adminControllers;

import com.barberia.app.models.Feedback;
import com.barberia.app.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminFeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/admin/feedback")
    public String goFeedbackPage(Model model){
        List<Feedback> feedbacks = feedbackService.findAll();
        model.addAttribute("feedbacks", feedbacks);
        return "admin/feedback";
    }

    @GetMapping("/admin/feedback/{feedbackId}")
    public String goFeedbackEmail(@PathVariable(value = "feedbackId") long feedbackId, Model model){
        Feedback feedback = feedbackService.findById(feedbackId).get();
        model.addAttribute("feedback", feedback);
        return "admin/feedback_prepare";
    }

    @PostMapping("/admin/feedback/send")
    public String sendEmail(Feedback feedback, @RequestParam(value = "content") String content, RedirectAttributes redirectAttributes){
        Feedback feedback1 = feedbackService.findById(feedback.getId()).get();
        feedback1.setSend(true);
        boolean message = true;
        redirectAttributes.addFlashAttribute("message", message);
        System.out.println(feedback.getEmail() +" gửi thành công" + content);
        feedbackService.save(feedback1);
        return "redirect:/admin/feedback";
    }
}
