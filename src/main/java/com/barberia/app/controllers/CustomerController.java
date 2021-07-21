package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Customer;
import com.barberia.app.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // GET ALL CUSTOMER
    @GetMapping("customers")
    public List<Customer> findAll(){
        return customerService.findAll();
    }
    // GET CUSTOMER BY ID
    @GetMapping("customers/{id}")
    public Optional<Customer> findById(@PathVariable Long id){
        return customerService.findById(id);
    }
    // CREATE CUSTOMER
    @PostMapping("customers")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }
    // UPDATE CUSTOMER
    @PutMapping("customers/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerUpdate){
        Customer customer = customerService.findById(id).get();
        customer.setPhoneNumber(customerUpdate.getPhoneNumber());
        customer.setPassword(customerUpdate.getPassword());
        customer.setNickName(customerUpdate.getNickName());
        customer.setEmail(customerUpdate.getEmail());
        customer.setMembership(customerUpdate.isMembership());
        customer.setMembershipName(customerUpdate.getMembershipName());

        return customerService.save(customer);
    }
    // DELETE CUSTOMER
    @DeleteMapping("customers/{id}")
    public void delete(@PathVariable Long id){
        customerService.delete(id);
    }
}
