package com.app.petclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>{
    
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
