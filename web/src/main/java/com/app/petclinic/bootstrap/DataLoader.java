package com.app.petclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.petclinic.model.Owner;
import com.app.petclinic.model.Pet;
import com.app.petclinic.model.PetType;
import com.app.petclinic.model.Speciality;
import com.app.petclinic.model.Vet;
import com.app.petclinic.model.Visit;
import com.app.petclinic.services.OwnerService;
import com.app.petclinic.services.VetService;
import com.app.petclinic.services.VisitService;
import com.app.petclinic.services.PetTypeService;
import com.app.petclinic.services.SpecialityService;


@Component
public class DataLoader implements CommandLineRunner{

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
            SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {

        PetType dog = new PetType();
        dog.setName("DoG");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radioLogy = new Speciality();
        radioLogy.setDescription("RadioLogist");
        Speciality savedRL = specialityService.save(radioLogy);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSG = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentist");
        Speciality savedDnts = specialityService.save(dentistry);
        
        Owner owner1 = new Owner();
        owner1.setFirstName("Mr");
        owner1.setLastName("A");

        owner1.setAddress("Wwwwwwwwwww");
        owner1.setCity("eeee");
        owner1.setTelephone("111111111");

        Pet aSpet = new Pet();
        aSpet.setName("AAAAA");
        aSpet.setBirthDate(LocalDate.now());
        aSpet.setOwner(owner1);
        aSpet.setPetType(savedDogPetType);

        owner1.getPets().add(aSpet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Mr");
        owner2.setLastName("B");

        owner2.setAddress("Wwwwwwwwwww");
        owner2.setCity("eeee");
        owner2.setTelephone("111111111");

        Pet bSpet = new Pet();
        bSpet.setName("BBBBB");
        bSpet.setBirthDate(LocalDate.now());
        bSpet.setOwner(owner2);
        bSpet.setPetType(savedCatPetType);

        owner2.getPets().add(bSpet);
        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(bSpet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Snezzy cat");

        visitService.save(catVisit);
        
        System.out.println("Owners loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Mr");
        vet1.setLastName("X");
        vet1.getSpecialities().add(savedRL);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mr");
        vet2.setLastName("Y");
        vet2.getSpecialities().add(savedSG);
        vetService.save(vet2);

        System.out.println("Vets loaded...");
    }
}