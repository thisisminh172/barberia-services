package com.barberia.app.adminControllers;

import com.barberia.app.files.FileStorageService;
import com.barberia.app.models.Employee;
import com.barberia.app.models.Service;
import com.barberia.app.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class AdminServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/admin/services")
    public String goServices(Model model){
        Service service = new Service();
        model.addAttribute("service",service);
        model.addAttribute("services", serviceService.findAll());
        return "admin/service";
    }

    @PostMapping("/admin/services/addNew")
    public String addNew(Service service, @RequestParam(value = "file") MultipartFile file){
        if(!file.isEmpty()){
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
            service.setThumbnail(fileDownloadUri);
        }
        serviceService.save(service);
        return "redirect:/admin/services";
    }

    @GetMapping("/admin/services/findById")
    public String goUpdateServiceForm(long id, Model model){
        Service service = serviceService.findById(id).get();
        model.addAttribute("service",service);
        return "/admin/service_update_form";
    }

    //update service
    @RequestMapping(value = "/admin/services/update", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateService(Service service, @RequestParam(value = "file") MultipartFile file){


        if(!file.isEmpty()){
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
            service.setThumbnail(fileDownloadUri);
        }

        serviceService.save(service);
        return "redirect:/admin/services";
    }

    @RequestMapping(value = "/admin/services/deactive", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deactive(long id){
        Service service = serviceService.findById(id).get();
        service.setAvailable(false);
        serviceService.save(service);
        return "redirect:/admin/services";
    }
}
