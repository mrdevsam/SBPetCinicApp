package com.app.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.petclinic.model.Owner;
import com.app.petclinic.model.Vet;
import com.app.petclinic.services.OwnerService;
import com.app.petclinic.services.VetService;
//import com.app.petclinic.services.map.OwnerServiceMap;
//import com.app.petclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner{

    private final OwnerService ownerService;
    private final VetService vetService;

    
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) throws Exception {
        
        Owner owner1 = new Owner();
        owner1.setFirstName("Mr");
        owner1.setLastName("A");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Mr");
        owner2.setLastName("B");
        ownerService.save(owner2);

        System.out.println("Owners loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Mr");
        vet1.setLastName("X");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mr");
        vet2.setLastName("Y");
        vetService.save(vet2);

        System.out.println("Vets loaded...");
    } 
}