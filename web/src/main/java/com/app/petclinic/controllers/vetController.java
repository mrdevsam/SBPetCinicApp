package com.app.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.petclinic.services.VetService;

@Controller
public class vetController {
    
    private final VetService vetService;

    
    public vetController(VetService vetService) {
        this.vetService = vetService;
    }


    @RequestMapping({"vets", "/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String vetIndex(Model model) {
        
        model.addAttribute("vets", vetService.findAll());
        return "vets/index.html";
    }
}
