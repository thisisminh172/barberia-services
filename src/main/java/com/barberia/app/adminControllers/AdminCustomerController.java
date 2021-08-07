package com.barberia.app.adminControllers;

import com.barberia.app.models.Customer;
import com.barberia.app.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminCustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/admin/customer")
    public String goCustomerPage(Model model){
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
}
