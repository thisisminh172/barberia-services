package com.barberia.app.adminControllers;

import com.barberia.app.dto.EmployeeAndTurnDto;
import com.barberia.app.dto.SalonUpdateDto;
import com.barberia.app.email.SendEmailService;
import com.barberia.app.models.*;
import com.barberia.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminHomeController {
    @Autowired
    private SalonService salonService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TurnService turnService;
    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private SendEmailService sendEmailService;


    @GetMapping(path = {"/admin/index","/admin","/admin/home"})
    public String goHome(Model model) {
        Salon salon = salonService.findById(1l).get();
        List<Booking> bookings = bookingService.findByStatus("online");
        List<Payment> payments = paymentService.findAll();
        List<Employee> employees = employeeService.findAll();
        List<Employee> employeesRoleStaff = employeeService.findStaff();
        List<Turn> turns = turnService.findAllDonePaymentTurn();
        List<Booking> listOfOnlineBookings = bookingService.findOnlineBookingAfterSpecificTime();

        List<EmployeeAndTurnDto> employeeAndTurnDtos = new ArrayList<EmployeeAndTurnDto>();
        for(int i = 0; i< employeesRoleStaff.size();i++){
            EmployeeAndTurnDto employeeAndTurnDto = new EmployeeAndTurnDto();
            employeeAndTurnDto.setEmployee(employeesRoleStaff.get(i));
            employeeAndTurnDto.setNumberOfTurns(turnService.findAllByStatusAndEmployeeId("done", employeesRoleStaff.get(i).getId()).size());
            employeeAndTurnDtos.add(employeeAndTurnDto);
        }


        double totalAllPayment = 0;

        for(int i = 0; i< payments.size(); i++){
            totalAllPayment += payments.get(i).getTotalPrice();
        }
        LocalDateTime today = LocalDateTime.now();
        model.addAttribute("today", today);
        model.addAttribute("totalAllPayment", totalAllPayment);
        model.addAttribute("numberOfOnlineBookings", bookings.size());
        model.addAttribute("numberOfEmployees", employees.size());
        model.addAttribute("numberOfPayments", payments.size());
        model.addAttribute("salon", salon);
        model.addAttribute("numberOfStaff", employeesRoleStaff.size());
        model.addAttribute("employeesRoleStaff", employeesRoleStaff);
        model.addAttribute("employeeAndTurnDtos", employeeAndTurnDtos);
        model.addAttribute("listOfOnlineBookings", listOfOnlineBookings);

        return "admin/index";
    }

    // GỬI TIN NHẮN CHO NHÂN VIÊN






    @GetMapping("/admin/salon")
    public String goSalonDetail(Model model){
        Salon salonDetails = salonService.findById(1l).get();

        model.addAttribute("salon", salonDetails);
        return "admin/salon";
    }

    @GetMapping("/admin/login")
    public String getLogin(){
        return "admin/login";
    }


}
