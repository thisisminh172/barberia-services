package com.barberia.app.clientControllers;

import com.barberia.app.models.HairStyle;
import com.barberia.app.services.HairStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClientHairStyleController {
    @Autowired
    private HairStyleService hairStyleService;
    @GetMapping("/hair-style")
    public String goHairStyle(Model model){
        List<HairStyle> hairStyles = hairStyleService.findAll();
        model.addAttribute("hairStyles", hairStyles);
        return "client/hair-style";
    }

    @GetMapping("/hair-style/{hairStyleId}")
    public String goHairStyleDetail(@PathVariable("hairStyleId") long hairStyleId, Model model){
        HairStyle hairStyle = hairStyleService.findById(hairStyleId).get();
        model.addAttribute("hairStyle", hairStyle);
        return "client/hair-style-detail";
    }
}
