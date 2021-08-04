package com.barberia.app.adminControllers;

import com.barberia.app.models.*;
import com.barberia.app.services.*;
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
    private BookingDetailService bookingDetailService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TurnService turnService;
    @Autowired
    private PaymentService paymentService;

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

    // HIỂN THỊ NHỮNG BOOKING ĐANG ĐƯỢC PHỤC VỤ
    @GetMapping("/admin/serving-page")
    public String goServingPage(Model model){
        List<Turn> turns = turnService.findAllNotYetPaymentTurn();
        model.addAttribute("turns", turns);
        return "admin/serving_booking";
    }

    // KHI NHÂN VIÊN NHẤN VÁO NÚT TIẾN HÀNH THANH TOÁN TRÊN 1 LƯỢT
    @GetMapping("/admin/check-out-page/")
    public String goCheckOutPage(long id, Model model){
        Turn turn = turnService.findById(id).get();
        Booking booking = turn.getBooking();
        Employee employee = turn.getEmployee();
        List<BookingDetail> bookingDetails = bookingDetailService.findByBookingId(booking.getId());
        double totalPrice =0;
        for (int i = 0; i< bookingDetails.size();i++){
            totalPrice += bookingDetails.get(i).getService().getOriginalPrice();
        }
        totalPrice = totalPrice*1000;
        model.addAttribute("turnId", turn.getId());
        model.addAttribute("booking", booking);
        model.addAttribute("employee", employee);
        model.addAttribute("bookingDetails", bookingDetails);
        model.addAttribute("totalPrice", totalPrice);
        return "admin/check_out";
    }

    @PostMapping("/admin/confirm-check-out-page")
    public String goConfirmCheckOutPage(@RequestParam("turnId") long turnId, @RequestParam("totalPrice") double totalPrice, @RequestParam("paymentMethod") String paymentMethod, Model model){
        Turn turn = turnService.findById(turnId).get();
        Booking booking = turn.getBooking();
        Employee employee = turn.getEmployee();

        model.addAttribute("turnId", turn.getId());
        model.addAttribute("booking", booking);
        model.addAttribute("employee", employee);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentMethod", paymentMethod);
        System.out.println(paymentMethod + " "+turnId);
        switch (paymentMethod){
            case "cash":
                return "admin/confirm_check_out_cash";
            default:
                return "admin/confirm_check_out_momo";
        }

    }

    @PostMapping("/admin/check-out/finish")
    public String finishPayment(@RequestParam("turnId") long turnId, @RequestParam("totalPrice") double totalPrice, @RequestParam("paymentMethod") String paymentMethod, Model model){
        Payment payment = new Payment();
        Turn turn = turnService.findById(turnId).get();
        turn.setStatus("done");
        turnService.save(turn);

        payment.setTurn(turn);
        payment.setPaymentMethod(paymentMethod);
        payment.setTotalPrice(totalPrice);

        paymentService.save(payment);

        return "redirect:/admin/serving-page";
    }

    @GetMapping("/admin/payment-list")
    public String goPaymentList(Model model){
        List<Payment> payments = paymentService.findAll();
        double totalAllPayment = 0;
        for(int i = 0; i< payments.size(); i++){
            totalAllPayment += payments.get(i).getTotalPrice();
        }
        model.addAttribute("payments",payments);
        model.addAttribute("totalAllPayment", totalAllPayment);
        return "admin/payment_list";

    }




}
