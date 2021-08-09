package com.barberia.app.adminControllers;

import com.barberia.app.dto.MessageDto;
import com.barberia.app.models.Payment;
import com.barberia.app.services.PaymentService;
import com.barberia.app.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class AdminPaymentController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/admin/payment-list")
    public String goPaymentList(Model model){
        MessageDto messageDto =(MessageDto) model.getAttribute("messageDto");
        if(messageDto != null){
            model.addAttribute("message", messageDto.getMessage());
            model.addAttribute("available", messageDto.isAvailable());
        }
        List<Payment> payments = paymentService.findAll();
        double totalAllPayment = 0;
        for(int i = 0; i< payments.size(); i++){
            totalAllPayment += payments.get(i).getTotalPrice();
        }
        model.addAttribute("payments",payments);
        model.addAttribute("totalAllPayment", totalAllPayment);
        return "admin/payment_list";

    }

    @GetMapping("/admin/report/{format}")
    public String generateReport(@PathVariable String format, RedirectAttributes redirectAttributes) throws FileNotFoundException, JRException {
//        return reportService.exportReport(format);
        String path = reportService.exportReport(format);
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Đã tạo thành công report: "+path);
        messageDto.setAvailable(true);
        redirectAttributes.addFlashAttribute("messageDto",messageDto);
        return "redirect:/admin/payment-list";
    }

    @GetMapping("/admin/payment-list/{paymentId}")
    public String goPaymentDetail(@PathVariable("paymentId") long paymentId, Model model){
        Payment payment = paymentService.findById(paymentId).get();
        model.addAttribute("payment", payment);
        return "admin/payment_detail";
    }
}
