package com.barberia.app.services;

import com.barberia.app.models.Employee;
import com.barberia.app.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    // GET ALL EMPLOYEE
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    // GET EMPLOYEE BY ID
    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }

    // DELETE EMPLOYEE
    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    // UPDATE EMPLOYEE
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findStaff(){
        return employeeRepository.findByRole("ROLE_STAFF");
    }
    public Employee findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }
}
