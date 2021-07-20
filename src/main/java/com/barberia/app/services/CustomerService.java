package com.barberia.app.services;

import com.barberia.app.models.Customer;
import com.barberia.app.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //GET ALL CUSTOMERS
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    // GET CUSTOMER BY ID
    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id);
    }

    // DELETE CUSTOMER
    public void delete(Long id){
        customerRepository.deleteById(id);
    }

    // UPDATE CUSTOMER
    public void save(Customer customer){
        customerRepository.save(customer);
    }
}
