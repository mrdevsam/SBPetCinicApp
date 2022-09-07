package com.app.petclinic.services.springdata;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.app.petclinic.model.Owner;
import com.app.petclinic.repositories.OwnerRepository;
import com.app.petclinic.repositories.PetRepository;
import com.app.petclinic.repositories.PetTypeRepository;
import com.app.petclinic.services.OwnerService;

@Service
@Profile("sbJpaOwner")
public class OwnerSbJpaService implements OwnerService{

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;
    
    public OwnerSbJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
            PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }
    
}
