package com.app.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.petclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long>{
    
}
