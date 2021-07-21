package com.barberia.app.services;

import com.barberia.app.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    // GET ALL SERVICE
    public List<com.barberia.app.models.Service> findAll(){
        return serviceRepository.findAll();
    }

    // GET SERVICE BY ID
    public Optional<com.barberia.app.models.Service> findById(Long id){
        return serviceRepository.findById(id);
    }

    // DELETE
    public void delete(Long id){
        serviceRepository.deleteById(id);
    }

    // UPDATE AND ADD
    public com.barberia.app.models.Service save(com.barberia.app.models.Service service){
        return serviceRepository.save(service);
    }
}
