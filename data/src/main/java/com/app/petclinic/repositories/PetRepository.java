package com.app.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{
    
}
