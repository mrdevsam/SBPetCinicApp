package com.app.petclinic.services.springdata;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.app.petclinic.model.Speciality;
import com.app.petclinic.repositories.SpecialityRepository;
import com.app.petclinic.services.SpecialityService;

@Service
@Profile("SpringData")
public class SpecialitySbJpaService implements SpecialityService{
    private final SpecialityRepository specialityRepository;

    public SpecialitySbJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }
}
