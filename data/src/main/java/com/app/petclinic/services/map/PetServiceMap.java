package com.app.petclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Profile;

import com.app.petclinic.model.Pet;
import com.app.petclinic.services.PetService;

@Service
@Profile({"default","mapBased"})
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService{

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }
    
}
