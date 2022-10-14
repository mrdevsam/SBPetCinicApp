package com.app.petclinic.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.petclinic.model.Owner;
import com.app.petclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
public class ownerControllerTest {

    @Mock
    OwnerService oService;

    @InjectMocks
    ownerController controller;

    Set<Owner> owners;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testOwFind() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notImplemented.html"));

        verifyNoInteractions(oService);
    }

    @Test
    void testOwnerIndex() throws Exception{
        when(oService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/index.html"))
        .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void testOwnersByIndex() throws Exception{
        when(oService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/index.html"))
        .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void testDisplayOwner() throws Exception{
        when(oService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/123"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

}
