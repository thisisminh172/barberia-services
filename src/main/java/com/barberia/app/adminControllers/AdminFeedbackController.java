package com.barberia.app.adminControllers;

import com.barberia.app.dto.MessageDto;
import com.barberia.app.email.SendEmailService;
import com.barberia.app.models.Feedback;
import com.barberia.app.services.FeedbackService;
import com.barberia.app.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class AdminFeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private ReportService reportService;

    @GetMapping("/admin/feedback")
    public String goFeedbackPage(Model model){
        MessageDto messageDto =(MessageDto) model.getAttribute("messageDto");
        if(messageDto != null){
            model.addAttribute("message1", messageDto.getMessage());
            model.addAttribute("available", messageDto.isAvailable());
        }
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
//        System.out.println(feedback.getEmail() +" gửi thành công" + content);

        feedbackService.save(feedback1);
        sendEmailService.sendEmail(feedback.getEmail(),"Phản hồi của khách: '"+feedback1.getComment()+"'---- Phản hồi:"+content+"' ---- Ký tên: quản lý!","BARBERIA - CẮT TÓC NAM");
        return "redirect:/admin/feedback";
    }

    @GetMapping("/admin/report/feedback/{format}")
    public String generateCancelBookingReport(@PathVariable String format, RedirectAttributes redirectAttributes) throws FileNotFoundException, JRException {
//        return reportService.exportReport(format);
        String path = reportService.exportFeedbackReport(format);
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully export report: "+path);
        messageDto.setAvailable(true);
        redirectAttributes.addFlashAttribute("messageDto",messageDto);
        return "redirect:/admin/feedback";
    }
}
