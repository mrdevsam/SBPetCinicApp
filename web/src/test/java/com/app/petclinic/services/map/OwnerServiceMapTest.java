package com.app.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.petclinic.model.Owner;

public class OwnerServiceMapTest {

    final Long ownerId = 1L;
    final String lastName = "Smith";

    OwnerServiceMap oServiceMap;

    @BeforeEach
    void setUp() {
        oServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        oServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }
    
    @Test
    void findAll() {
        Set<Owner> ownerSet = oServiceMap.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = oServiceMap.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = oServiceMap.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = oServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        oServiceMap.delete(oServiceMap.findById(ownerId));

        assertEquals(0, oServiceMap.findAll().size());
    }
    @Test
    void deleteById() {
        oServiceMap.deleteById(ownerId);

        assertEquals(0, oServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith = oServiceMap.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = oServiceMap.findByLastName("foo");
        
        assertNull(smith);
    }

}
