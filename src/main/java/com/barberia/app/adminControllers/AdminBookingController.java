package com.barberia.app.adminControllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Employee;
import com.barberia.app.models.Turn;
import com.barberia.app.services.BookingService;
import com.barberia.app.services.EmployeeService;
import com.barberia.app.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AdminBookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TurnService turnService;

    @GetMapping("/admin/online-bookings")
    public String goBooking(Model model){
        List<Booking> onlineBookingList = bookingService.findOnlineBookingAfterSpecificTime();
        LocalDateTime today = LocalDateTime.now();
        model.addAttribute("today", today);
        model.addAttribute("bookings", onlineBookingList);
        return "admin/online_booking";
    }
    @GetMapping("/admin/online-bookings/check-in-status")
    public String changeStatusToCheckIn(long id) {
        Booking booking = bookingService.findById(id).get();
        booking.setStatus("check-in");
        bookingService.save(booking);
        return "redirect:/admin/check-in-bookings";
    }

    @GetMapping("/admin/check-in-bookings")
    public String goCheckInBooking(Model model){
        List<Booking> checkInBookingList = bookingService.findCheckInBookingAfterSpecificTime();
        LocalDateTime today = LocalDateTime.now();
        model.addAttribute("today", today);
        model.addAttribute("bookings", checkInBookingList);
        return "admin/check_in_booking";
    }

    @GetMapping("/admin/arrange-staff-page")
    public String goArrange(long id, Model model){

        Booking booking = bookingService.findById(id).get();
        List<Employee> staffs = employeeService.findStaff();
        System.out.println(booking.getChosenTimeSlot());
        model.addAttribute("staffs", staffs);
        model.addAttribute("bookingTemp", booking);

        return "admin/arrange_staff";
    }

    @RequestMapping(value = "/admin/check-in-booking/update-staff", method = RequestMethod.POST)
    public String updateEmployeeWithTurn(@RequestParam("bookingId") long bookingId,@RequestParam("employeeId") long employeeId, Model model){
        Turn turn = new Turn();
        Booking booking = bookingService.findById(bookingId).get();
        booking.setStatus("serving");
        turn.setBooking(booking);
        turn.setEmployee(employeeService.findById(employeeId));
        turn.setStatus("notyet");
        turnService.save(turn);
        return "redirect:/admin/serving-page";
    }

    @GetMapping("/admin/serving-page")
    public String goServingPage(Model model){
        List<Turn> turns = turnService.findAllNotYetPaymentTurn();
        model.addAttribute("turns", turns);
        return "admin/serving_booking";
    }

    @GetMapping("/admin/check-out-page/")
    public String goCheckOutPage(long id){
        System.out.println(id);
        return "admin/check_out";
    }


}
