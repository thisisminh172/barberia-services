package com.barberia.app.adminControllers;

import com.barberia.app.dto.SalonUpdateDto;
import com.barberia.app.models.Salon;
import com.barberia.app.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminHomeController {
    @Autowired
    private SalonService salonService;

    @GetMapping(path = {"/admin/index","/admin","/admin/home"})
    public String goHome() {
        return "admin/index";
    }




    @GetMapping("/admin/salon")
    public String goSalonDetail(Model model){
        Salon salonDetails = salonService.findById(1l).get();

        model.addAttribute("salon", salonDetails);
        return "admin/salon";
    }


}
