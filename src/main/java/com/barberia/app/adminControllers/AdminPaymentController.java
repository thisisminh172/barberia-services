package com.barberia.app.adminControllers;

import com.barberia.app.dto.MessageDto;
import com.barberia.app.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;

@Controller
public class AdminPaymentController {
    @Autowired
    private ReportService reportService;

//    @GetMapping("/admin/report/{format}")
//    public String generateReport(@PathVariable String format, RedirectAttributes redirectAttributes) throws FileNotFoundException, JRException {
////        return reportService.exportReport(format);
//        String path = reportService.exportReport(format);
//        MessageDto messageDto = new MessageDto();
//        messageDto.setMessage("Đã tạo thành công report: "+path);
//        messageDto.setAvailable(true);
//        redirectAttributes.addFlashAttribute("messageDto",messageDto);
//        return "redirect:/admin/payment-list";
//    }
}
