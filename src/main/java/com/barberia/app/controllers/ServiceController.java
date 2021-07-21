package com.barberia.app.controllers;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Service;
import com.barberia.app.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    // GET ALL SERVICES
    @GetMapping("services")
    public List<Service> findAll(){
        return serviceService.findAll();
    }
    // GET SERVICE BY ID
    @GetMapping("services/{id}")
    public Optional<Service> findById(@PathVariable Long id){
        return serviceService.findById(id);
    }
    // CREATE SERVICE
    @PostMapping("services")
    public Service createServices(@RequestBody Service service){
        return serviceService.save(service);
    }
    // UPDATE SERVICE
    @PutMapping("services/{id}")
    public Service updateService(@PathVariable Long id, @RequestBody Service serviceUpdate){
        Service service = serviceService.findById(id).get();
        service.setServiceName(serviceUpdate.getServiceName());
        service.setOriginalPrice(serviceUpdate.getOriginalPrice());
        service.setDiscountPrice(serviceUpdate.getDiscountPrice());
        service.setDiscount(serviceUpdate.isDiscount());
        service.setTimeConsume(serviceUpdate.getTimeConsume());
        service.setDescription(serviceUpdate.getDescription());

        return serviceService.save(service);
    }
    // DELETE SERVICE
    @DeleteMapping("services/{id}")
    public void delete(@PathVariable Long id){
        serviceService.delete(id);
    }
}
