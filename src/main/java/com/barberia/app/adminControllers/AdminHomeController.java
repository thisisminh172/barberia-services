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


    @GetMapping("/admin/bookings")
    public String goBooking(){
        return "admin/booking";
    }

    @GetMapping("/admin/salon")
    public String goSalonDetail(Model model){
        Salon salonDetails = salonService.findById(1l).get();
        SalonUpdateDto salonUpdateDto = new SalonUpdateDto();

        salonUpdateDto.setId(salonDetails.getId());
        salonUpdateDto.setSalonName(salonDetails.getSalonName());
        salonUpdateDto.setAddress(salonDetails.getAddress());
        salonUpdateDto.setEmail(salonDetails.getEmail());
        salonUpdateDto.setThumbnailUrl(salonDetails.getThumbnailUrl());
        salonUpdateDto.setPhoneNumber(salonDetails.getPhoneNumber());
        salonUpdateDto.setOpenedHour(salonDetails.getOpenedHour());
        salonUpdateDto.setClosedHour(salonDetails.getClosedHour());
        salonUpdateDto.setMinuteInOneTimeSlot(salonDetails.getMinuteInOneTimeSlot());
        salonUpdateDto.setIsAvailableForOnlineBooking(salonDetails.isAvailableForOnlineBooking()==true?"true":"false");
        salonUpdateDto.setNumberOfTurnInOneTimeSlot(salonDetails.getNumberOfTurnInOneTimeSlot());
        salonUpdateDto.setIsOpened(salonDetails.isOpened()==true?"true":"false");
        salonUpdateDto.setIsCarParkingAvailable(salonDetails.isCarParkingAvailable()==true?"true":"false");
        model.addAttribute("salon", salonUpdateDto);
        return "admin/salon";
    }


}