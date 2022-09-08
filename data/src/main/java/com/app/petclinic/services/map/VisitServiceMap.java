package com.app.petclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Profile;

import com.app.petclinic.model.Visit;
import com.app.petclinic.services.VisitService;

@Service
@Profile({"default","mapBased"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService{

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit object) {

        if (object.getPet() == null || object.getPet().getId() == null || object.getPet().getOwner() == null || object.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid visit");
        }

        return super.save(object);
    }
    
}
