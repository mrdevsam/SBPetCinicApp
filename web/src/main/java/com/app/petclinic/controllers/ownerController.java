package com.app.petclinic.controllers;

import com.app.petclinic.model.Owner;
import com.app.petclinic.services.OwnerService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class ownerController {
    
    private static String VIEWS_OWNER_CREATE_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;
    
    public ownerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/owners/find")
    public String OwFind(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    //@GetMapping({"owners", "/owners", "/owners/index", "/owners/index.html"})
    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName("");//empty string signifies broadest possible search
        }

        //find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1){
            //if one record found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            //multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping({"/owners/{ownerId}"})
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/owners/new")
    public String initOwnerCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_UPDATE_FORM;
    }

    @PostMapping("/owners/new")
    public String processOwnerCreationForm(@Validated Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_UPDATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initOwnerUpdateForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/edit")
    public String processOwnerUpdateForm(@Validated Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}
