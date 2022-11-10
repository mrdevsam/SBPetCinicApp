package com.app.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.petclinic.model.Pet;

public class PetServiceMapTests {

    private PetServiceMap petServiceMap;
    private final Long petId = 1L;

    @BeforeEach
    void setUp() throws Exception{
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder().id(petId).build());
    }

    @Test
    void testFindAll() throws Exception{
        Set<Pet> petSet = petServiceMap.findAll();
        assertEquals(1, petSet.size());
    }

    @Test
    void testFindByIdExisting() throws Exception{
        Pet pet = petServiceMap.findById(petId);
        assertEquals(petId, pet.getId());
    }

    @Test
    void testFindByIdNonExisting() throws Exception{
        Pet pet = petServiceMap.findById(7L);
        assertNull(pet);
    }

    @Test
    void testFindByIdNull() {
        Pet pet = petServiceMap.findById(null);
        assertNull(pet);
    }

    @Test
    void testSaveExistingId() throws Exception{
        Long id = 2L;
        Pet pet2 = Pet.builder().id(id).build();
        Pet savedPet = petServiceMap.save(pet2);
        assertEquals(id, savedPet.getId());
    }

    @Test
    void testSaveDuplicatedId() throws Exception{
        Long id = 1L;
        Pet pet2 = Pet.builder().id(id).build();
        Pet savedPet = petServiceMap.save(pet2);
        assertEquals(id, savedPet.getId());
        assertEquals(1,petServiceMap.findAll().size());
    }
    
    @Test
    void testSaveNoId() throws Exception{
        Pet savedPet = petServiceMap.save(Pet.builder().build());
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petServiceMap.findAll().size());
    }

    @Test
    void testDeletePet() throws Exception{
        petServiceMap.delete(petServiceMap.findById(petId));
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void testDeleteWithWrongId() throws Exception{
        Pet pet = Pet.builder().id(7L).build();
        petServiceMap.delete(pet);
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void testDeleteWithNullId() throws Exception{
        Pet pet = Pet.builder().build();
        petServiceMap.delete(pet);
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void testDeleteNull() throws Exception{
        petServiceMap.delete(null);
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void testDeleteByIdCorrect() throws Exception{
        petServiceMap.deleteById(petId);
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void testDeleteByIdWrong() throws Exception{
        petServiceMap.deleteById(7L);
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void testDeleteByIdNull() throws Exception{
        petServiceMap.deleteById(null);
        assertEquals(1, petServiceMap.findAll().size());
    }
}
