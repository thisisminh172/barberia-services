package com.barberia.app.adminControllers;

import com.barberia.app.dto.SalonUpdateDto;
import com.barberia.app.models.Salon;
import com.barberia.app.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminSalonController {
    @Autowired
    private SalonService salonService;

    @GetMapping("/admin/salon/update-form")
    public String goSalonUpdateForm(Model model){
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
        return "admin/salon_update_form";
    }

    @RequestMapping(value="/admin/salon/update", method= {RequestMethod.PUT, RequestMethod.GET})
    public String updateSalon(@ModelAttribute("salon") SalonUpdateDto salonUpdateDto){
        System.out.println(salonUpdateDto.getId() +" "+  salonUpdateDto.getOpenedHour()+" "+ salonUpdateDto.getIsAvailableForOnlineBooking()+" "+salonUpdateDto.getIsCarParkingAvailable());
        Salon salonDetails = salonService.findById(1l).get();

        salonDetails.setSalonName(salonDetails.getSalonName());
        salonDetails.setAddress(salonDetails.getAddress());
        salonDetails.setEmail(salonDetails.getEmail());
        salonDetails.setThumbnailUrl(salonDetails.getThumbnailUrl());
        salonDetails.setPhoneNumber(salonDetails.getPhoneNumber());
        salonDetails.setOpenedHour(salonDetails.getOpenedHour());
        salonDetails.setClosedHour(salonDetails.getClosedHour());
        salonDetails.setMinuteInOneTimeSlot(salonDetails.getMinuteInOneTimeSlot());
        salonDetails.setAvailableForOnlineBooking(salonUpdateDto.getIsAvailableForOnlineBooking().equals("true")?true:false);
        salonDetails.setNumberOfTurnInOneTimeSlot(salonDetails.getNumberOfTurnInOneTimeSlot());
        salonDetails.setOpened(salonUpdateDto.getIsOpened().equals("true")?true:false);
        salonDetails.setCarParkingAvailable(salonUpdateDto.getIsCarParkingAvailable().equals("true")?true:false);

        salonService.save(salonDetails);
        return "redirect:/admin/salon";
    }

}
