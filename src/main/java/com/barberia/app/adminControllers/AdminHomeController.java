package com.barberia.app.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

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
}
