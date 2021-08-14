package com.barberia.app.adminControllers;

import com.barberia.app.dto.MessageDto;
import com.barberia.app.models.Customer;
import com.barberia.app.services.CustomerService;
import com.barberia.app.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class AdminCustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReportService reportService;

    @GetMapping("/admin/customer")
    public String goCustomerPage(Model model){
        MessageDto messageDto =(MessageDto) model.getAttribute("messageDto");
        if(messageDto != null){
            model.addAttribute("message", messageDto.getMessage());
            model.addAttribute("available", messageDto.isAvailable());
        }
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "admin/customer";
    }

    @GetMapping("/admin/customer/{customerId}")
    public String goCustomerDetail(@PathVariable("customerId") long customerId, Model model){
        Customer customer = customerService.findById(customerId).get();
        model.addAttribute("customer", customer);
        return "admin/customer_detail";
    }

    @GetMapping("/admin/report/customer/{format}")
    public String generateCancelBookingReport(@PathVariable String format, RedirectAttributes redirectAttributes) throws FileNotFoundException, JRException {
//        return reportService.exportReport(format);
        String path = reportService.exportCustomerReport(format);
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully export report: "+path);
        messageDto.setAvailable(true);
        redirectAttributes.addFlashAttribute("messageDto",messageDto);
        return "redirect:/admin/customer";
    }
}
