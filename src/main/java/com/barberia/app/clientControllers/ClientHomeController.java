package com.barberia.app.clientControllers;

import com.barberia.app.HelperFunction.MyFunction;
import com.barberia.app.models.Booking;
import com.barberia.app.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ClientHomeController {
    @Autowired
    private BookingService bookingService;

    @RequestMapping(path = {"/home", "/"})
    public String index() {

        return "client/home";
    }

    @RequestMapping("/contact")
    public String showContact(Model model) {
        boolean message = false;
        model.addAttribute("message", message);
        return "client/contact";
    }



    @RequestMapping("/about")
    public String showAbout() {
        return "client/about";
    }




    @RequestMapping("/hair-style")
    public String showHairStyle() {
        return "client/hair-style";
    }
}
