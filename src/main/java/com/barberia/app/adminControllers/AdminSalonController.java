package com.barberia.app.adminControllers;

import com.barberia.app.dto.SalonUpdateDto;
import com.barberia.app.models.Salon;
import com.barberia.app.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminSalonController {
    @Autowired
    private SalonService salonService;

    @GetMapping("/admin/salon/update-form")
    public String goSalonUpdateForm(Model model){
        Salon salonDetails = salonService.findById(1l).get();
        System.out.println(salonDetails.getClosedHour());


        model.addAttribute("salon", salonDetails);
        return "admin/salon_update_form";
    }

    @RequestMapping(value="/admin/salon/update", method= {RequestMethod.PUT, RequestMethod.GET})
    public String updateSalon(@ModelAttribute("salon") Salon salonDetails){


        salonService.save(salonDetails);
        return "redirect:/admin/salon";
    }

}
