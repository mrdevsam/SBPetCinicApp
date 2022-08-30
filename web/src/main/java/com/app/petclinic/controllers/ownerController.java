package com.app.petclinic.controllers;

import com.app.petclinic.services.OwnerService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class ownerController {
    
    private final OwnerService ownerService;
    
    public ownerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @RequestMapping({"owners", "/owners","/owners/index", "/owners/index.html"})
    public String ownerIndex(Model model) {
        
        model.addAttribute("owners", ownerService.findAll());
        
        return "owners/index.html";
    }

    @RequestMapping("/owners/find")
    public String OwFind() {
        return "notImplemented.html";
    }
}
