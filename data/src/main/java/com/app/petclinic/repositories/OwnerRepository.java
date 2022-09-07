package com.app.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>{
    
}
