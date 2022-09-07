package com.app.petclinic.services.springdata;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.app.petclinic.model.Vet;
import com.app.petclinic.repositories.VetRepository;
import com.app.petclinic.services.VetService;

@Service
@Profile("SpringData")
public class VetSbJpaService implements VetService{
    private final VetRepository vetRepository;

    public VetSbJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }
}
