package com.barberia.app.adminControllers;

import com.barberia.app.dto.EmployeeAndTurnDto;
import com.barberia.app.email.SendEmailService;
import com.barberia.app.files.FileStorageService;
import com.barberia.app.models.Employee;
import com.barberia.app.models.Payment;
import com.barberia.app.models.Turn;
import com.barberia.app.services.EmployeeService;

import com.barberia.app.services.PaymentService;
import com.barberia.app.services.TurnService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminEmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private TurnService turnService;
    @Autowired
    private PaymentService paymentService;

    private List<String> roleList = Arrays.asList("ROLE_ADMIN","ROLE_STAFF","ROLE_MANAGER","ROLE_VISITOR");

    @GetMapping("/admin/employees")
    public String goEmployees(Model model){
        Employee employee = new Employee();
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("employee", employee);
        model.addAttribute("roleList", roleList);
        return "admin/employee";
    }

    @PostMapping("/admin/employees")
    public String addNew(@ModelAttribute(value = "employee") Employee employee, @RequestParam(value = "file") MultipartFile file){

        //Kiem tra file
        if(!file.isEmpty()){
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
            employee.setThumbnailUrl(fileDownloadUri);
        }
        employeeService.save(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/admin/employees/findById")
    public String goUpdateEmployeeForm(long id, Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        model.addAttribute("roleList", roleList);
        return "/admin/employee_update_form";
    }

    @RequestMapping(value = "/admin/employees/update", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateEmployee(Employee employee, @RequestParam(value = "file") MultipartFile file){
//        System.out.println(employee.isActive()+" "+employee.isOnlineBookingAvailable());
        if(!file.isEmpty()){
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
            employee.setThumbnailUrl(fileDownloadUri);
        }
        employeeService.save(employee);
        return "redirect:/admin/employees";
    }

    @RequestMapping(value = "/admin/employees/deactive", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deactive(long id){
        Employee employee = employeeService.findById(id);
        employee.setActive(false);
        employeeService.save(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/admin/employees/sendmail/{employeeId}")
    public String goSendMailPage(@PathVariable(value = "employeeId") long employeeId, Model model){
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        return "admin/employee_email";
    }

    @PostMapping("/admin/employees/sendmail")
    public String sendEmail(@RequestParam(value = "email") String email, @RequestParam(value = "content") String content, RedirectAttributes redirectAttributes){
        boolean message = true;
        redirectAttributes.addFlashAttribute("message", message);
        sendEmailService.sendEmail(email, content+"' ---- Ký tên: quản lý!", "BARBERIA - QUẢN LÝ");
        return "redirect:/admin/employees";
    }

    @GetMapping("/admin/employees/salary")
    public String goToSalaryCalculate(Model model){

        List<LocalDateTime> localDateTimeList = new ArrayList<LocalDateTime>();
        int currentYear = LocalDateTime.now().getYear();
        for(int i = 1; i <= 12; i++){
            LocalDateTime tempDate = LocalDateTime.of(currentYear,i,1,0,0);
            localDateTimeList.add(tempDate);
//            System.out.println(tempDate);
        }


        List<Employee> employeesRoleStaff = employeeService.findStaff();
        List<EmployeeAndTurnDto> employeeAndTurnDtos = new ArrayList<EmployeeAndTurnDto>();
        for(int i = 0; i< employeesRoleStaff.size();i++){
            EmployeeAndTurnDto employeeAndTurnDto = new EmployeeAndTurnDto();
            employeeAndTurnDto.setEmployee(employeesRoleStaff.get(i));
            employeeAndTurnDto.setNumberOfTurns(turnService.findAllByStatusAndEmployeeId("done", employeesRoleStaff.get(i).getId()).size());
            employeeAndTurnDto.setTurns(turnService.findAllByStatusAndEmployeeId("done", employeesRoleStaff.get(i).getId()));
            employeeAndTurnDtos.add(employeeAndTurnDto);
        }
        model.addAttribute("employeeAndTurnDtos",employeeAndTurnDtos);
        model.addAttribute("localDateTimeList",localDateTimeList);
        return "admin/employee_salary";
    }

    @RequestMapping(value = "/admin/employees/salary/calculate", method = RequestMethod.POST)
    public String goToSalaryCalculateDetail(@RequestParam(value = "id") Long id, @RequestParam(value = "month") String month, Model model){
        Employee employee = employeeService.findById(id);
        LocalDateTime chosenMonth = LocalDateTime.parse(month);
        LocalDateTime beforeMonth = chosenMonth.minusDays(1);

        LocalDateTime nextMonth = chosenMonth.plusMonths(1);
//        System.out.println(beforeMonth);
//        System.out.println(nextMonth);
        List<Turn> allTurns = turnService.findAllByStatusAndEmployeeId("done", id);
        List<Turn> turnListOfMonth = new ArrayList<Turn>();
        for(int i = 0; i< allTurns.size(); i++){
//            System.out.println(allTurns.get(i).getBooking().getChosenTimeSlot());
//            System.out.println(allTurns.get(i).getBooking().getChosenTimeSlot().isBefore(nextMonth));
//            System.out.println(allTurns.get(i).getBooking().getChosenTimeSlot().isAfter(beforeMonth));
            if(allTurns.get(i).getBooking().getChosenTimeSlot().isBefore(nextMonth) && allTurns.get(i).getBooking().getChosenTimeSlot().isAfter(beforeMonth)){
                turnListOfMonth.add(allTurns.get(i));
                System.out.println(allTurns.get(i).getBooking().getChosenTimeSlot());
            }
        }


        List<Payment> listOfEmployeePayments = new ArrayList<Payment>();
        for(Turn turn : turnListOfMonth){
            List<Payment> tempList = paymentService.findByTurnId(turn.getId());
            for(int i = 0; i< tempList.size();i++){
                listOfEmployeePayments.add(tempList.get(i));
//                System.out.println(tempList.get(i).getTotalPrice());
            }
        }
        double totalAllPayment = 0;
        for(int i = 0; i< listOfEmployeePayments.size(); i++){
            totalAllPayment += listOfEmployeePayments.get(i).getTotalPrice();
        }
        model.addAttribute("startMonth", chosenMonth);
        model.addAttribute("endMonth", nextMonth.minusDays(1));
        model.addAttribute("totalAllPayment",totalAllPayment);
        model.addAttribute("totalMake", totalAllPayment*0.6);
        model.addAttribute("employee", employee);
        model.addAttribute("listOfEmployeePayments", listOfEmployeePayments);
        return "admin/employee_salary_detail";
    }
}
