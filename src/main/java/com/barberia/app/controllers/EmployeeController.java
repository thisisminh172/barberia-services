package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Employee;
import com.barberia.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    // GET ALL EMPLOYEE
    @GetMapping("employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // GET EMPLOYEE BY ID
    @GetMapping("employees/{id}")
    public Employee findById(@PathVariable Long id){
        return employeeService.findById(id);
    }
    // CREATE EMPLOYEE
    @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }
    // UPDATE EMPLOYEE
    @PutMapping("employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeUpdate){
        Employee employee = employeeService.findById(id);
        employee.setNickName(employeeUpdate.getNickName());
        employee.setPhoneNumber(employeeUpdate.getPhoneNumber());
        employee.setPassword(employeeUpdate.getPassword());
        employee.setFirstName(employeeUpdate.getFirstName());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setRole(employeeUpdate.getRole());
        employee.setGender(employeeUpdate.getGender());
        employee.setActive(employeeUpdate.isActive());
        employee.setEmail(employeeUpdate.getEmail());
        employee.setHomeAddress(employeeUpdate.getHomeAddress());
        employee.setDateOfBirth(employeeUpdate.getDateOfBirth());
        employee.setOnlineBookingAvailable(employeeUpdate.isOnlineBookingAvailable());

        return employeeService.save(employee);
    }
    // DELETE EMPLOYEE
    @DeleteMapping("employees/{id}")
    public void delete(@PathVariable Long id){
        employeeService.delete(id);
    }
}
