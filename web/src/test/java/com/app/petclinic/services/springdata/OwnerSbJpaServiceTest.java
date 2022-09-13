package com.app.petclinic.services.springdata;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.petclinic.model.Owner;
import com.app.petclinic.repositories.OwnerRepository;
import com.app.petclinic.repositories.PetRepository;
import com.app.petclinic.repositories.PetTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class OwnerSbJpaServiceTest {

    public static final String Last_Name = "Smith";

    @Mock
    OwnerRepository oRepository;

    @Mock
    PetRepository pRepository;

    @Mock
    PetTypeRepository pTypeRepository;

    @InjectMocks
    OwnerSbJpaService service;

    Owner returnOwner;
    
    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(Last_Name).build();
    }

    @Test
    void findByLastName() {
        when(oRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(Last_Name);

        assertEquals(Last_Name, smith.getLastName());
        verify(oRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());

        when(oRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(oRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(oRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();
        
        when(oRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
        verify(oRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        //default is 1 times
        verify(oRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(oRepository).deleteById(anyLong());
    }
}
