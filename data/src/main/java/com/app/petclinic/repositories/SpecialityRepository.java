package com.app.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.petclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long>{
    
}
