package com.app.petclinic.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.petclinic.model.Vet;
import com.app.petclinic.services.VetService;

@Controller
public class vetController {

    private final VetService vetService;

    public vetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({ "vets", "/vets", "/vets/index", "/vets/index.html", "/vets.html" })
    public String vetIndex(Model model) {

        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson() {
        return vetService.findAll();
    }
}
