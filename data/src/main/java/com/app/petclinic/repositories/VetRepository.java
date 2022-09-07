package com.app.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.petclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long>{
    
}
