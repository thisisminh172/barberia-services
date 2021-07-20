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
    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    // DELETE EMPLOYEE
    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    // UPDATE EMPLOYEE
    public void save(Employee employee){
        employeeRepository.save(employee);
    }
}
