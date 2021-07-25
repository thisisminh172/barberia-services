package com.barberia.app.clientControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientHomeController {

    @RequestMapping(path = {"/home", "/"})
    public String index() {
        return "client/home";
    }

    @RequestMapping("/contact")
    public String showContact() {
        return "client/contact";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "client/login";
    }

    @RequestMapping("/about")
    public String showAbout() {
        return "client/about";
    }

    @RequestMapping("/service")
    public String showService() {
        return "client/service";
    }

    @RequestMapping("/booking")
    public String showBooking() {
        return "client/booking";
    }

    @RequestMapping("/hair-style")
    public String showHairStyle() {
        return "client/hair-style";
    }
}
