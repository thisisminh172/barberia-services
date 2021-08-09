package com.barberia.app.adminControllers;

import com.barberia.app.email.SendEmailService;
import com.barberia.app.files.FileStorageService;
import com.barberia.app.models.Employee;
import com.barberia.app.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
}
