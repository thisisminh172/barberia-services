package com.barberia.app.adminControllers;

import com.barberia.app.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
    @Autowired
    private SalonService salonService;

    @GetMapping(path = {"/index","","/home"})
    public String goHome() {
        return "admin/index";
    }

    @GetMapping("/employees")
    public String goEmployee(){
        return "admin/employee";
    }

    @GetMapping("/bookings")
    public String goBooking(){
        return "admin/booking";
    }

    @GetMapping("/salon")
    public String goSalonDetail(Model model){
        model.addAttribute("salon", salonService.findById(1l).get());
        return "admin/salon";
    }
    @GetMapping("/salon/update-form")
    public String goSalonUpdateForm(){
        return "admin/salon_update_form";
    }
}
