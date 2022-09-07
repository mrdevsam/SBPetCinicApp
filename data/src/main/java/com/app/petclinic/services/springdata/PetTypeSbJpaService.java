package com.app.petclinic.services.springdata;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.app.petclinic.model.PetType;
import com.app.petclinic.repositories.PetTypeRepository;
import com.app.petclinic.services.PetTypeService;

@Service
@Profile("SpringData")
public class PetTypeSbJpaService implements PetTypeService{
    
    private final PetTypeRepository petTypeRepository;

    public PetTypeSbJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }
    
    
}
