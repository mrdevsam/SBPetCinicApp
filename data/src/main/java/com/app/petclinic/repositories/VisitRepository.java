package com.app.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long>{
    
}
