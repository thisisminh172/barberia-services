package com.barberia.app.clientControllers;

import com.barberia.app.models.Service;
import com.barberia.app.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientServiceController {
    @Autowired
    private ServiceService serviceService;
    @RequestMapping("/service")
    public String showService(Model model) {
        List<Service> serviceList = serviceService.findAll();
        model.addAttribute("services", serviceList);
        return "client/service";
    }
}
