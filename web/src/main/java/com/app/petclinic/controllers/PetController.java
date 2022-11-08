package com.app.petclinic.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.petclinic.model.Owner;
import com.app.petclinic.model.Pet;
import com.app.petclinic.model.PetType;
import com.app.petclinic.services.OwnerService;
import com.app.petclinic.services.PetService;
import com.app.petclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEWS_CREARE_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }
    
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initPetCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_CREARE_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String proccessPetCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), false) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        pet.setOwner(owner);
        //pet.setBirthDate(LocalDate.now());
        owner.getPets().add(pet);

        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_CREARE_UPDATE_PET_FORM;
        } else {
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initPetUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_CREARE_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processPetUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_CREARE_UPDATE_PET_FORM;
        } else {
            //owner.getPets().add(pet);
            pet.setOwner(owner);
            //pet.setBirthDate(LocalDate.now());
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
