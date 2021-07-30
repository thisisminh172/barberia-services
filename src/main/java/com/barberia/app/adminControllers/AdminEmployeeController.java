package com.barberia.app.adminControllers;

import com.barberia.app.models.Employee;
import com.barberia.app.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminEmployeeController {
    @Autowired
    private EmployeeService employeeService;

    private List<String> roleList = Arrays.asList("ROLE_ADMIN","ROLE_STAFF","ROLE_MANAGER");

    @GetMapping("/admin/employees")
    public String goEmployees(Model model){
        Employee employee = new Employee();
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("employee", employee);
        model.addAttribute("roleList", roleList);
        return "admin/employee";
    }

    @PostMapping("/admin/employees/addNew")
    public String addNew(@ModelAttribute("employee") Employee employee){
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

    @RequestMapping(value = "/admin/employees/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateEmployee(Employee employee){
        System.out.println(employee.isActive()+" "+employee.isOnlineBookingAvailable());
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
}
