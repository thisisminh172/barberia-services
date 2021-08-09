package com.barberia.app.adminControllers;

import com.barberia.app.HelperFunction.MyFunction;
import com.barberia.app.files.FileStorageService;
import com.barberia.app.models.HairStyle;
import com.barberia.app.services.HairStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Controller
public class AdminHairStyleController {

    @Autowired
    private HairStyleService hairStyleService;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/admin/hair-style")
    public String goHairStyleList(Model model){
        List<HairStyle> hairStyles = hairStyleService.findAll();
        model.addAttribute("hairStyles", hairStyles);
        return "admin/hair_style";
    }

    @PostMapping("/admin/hair-style")
    public String addNewHairStyle(@RequestParam(value = "title") String title, @RequestParam(value = "description") String description, @RequestParam(value = "file") MultipartFile file){
        HairStyle hairStyle = new HairStyle();
        hairStyle.setTitle(title);
        hairStyle.setDescription(description);
        System.out.println(title+"  "+description);
        //Kiem tra file
        if(!file.isEmpty()){
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
            hairStyle.setThumbnail(fileDownloadUri);
        }


        hairStyleService.save(hairStyle);
        return "redirect:/admin/hair-style";
    }

    @GetMapping("/admin/hair-style/{hairStyleId}")
    public String goUpdateHairStyle(@PathVariable("hairStyleId") long hairStyleId, Model model){
        HairStyle hairStyle = hairStyleService.findById(hairStyleId).get();
        model.addAttribute("hairStyle", hairStyle);
        return "admin/hair_style_update";
    }



    @RequestMapping(value = "/admin/hair-style/update", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateHairStyle(HairStyle hairStyleUpdate, @RequestParam(value = "file")MultipartFile file){

        if(!file.isEmpty()){
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
            hairStyleUpdate.setThumbnail(fileDownloadUri);
        }

        hairStyleService.save(hairStyleUpdate);
        return "redirect:/admin/hair-style";
    }


    @RequestMapping(value = "/admin/hair-style/delete/{hairStyleId}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteHairStyle(@PathVariable("hairStyleId") long hairStyleId){
//        HairStyle hairStyle = hairStyleService.findById(hairStyleId).get();
        hairStyleService.delete(hairStyleId);
        return "redirect:/admin/hair-style";
    }
}
