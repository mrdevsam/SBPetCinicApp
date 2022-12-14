package com.app.petclinic.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
    void testFindOwner() throws Exception {
        mockMvc.perform(get("/owners/find"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/findOwners"))
        .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(oService);
    }

    @Test
    void testProcessFindFormReturnMany() throws Exception{
        when(oService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build(), Owner.builder().id(2L).build()));
        
        mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownersList"))
        .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void testProcessFindFormReturnOne() throws Exception {
        when(oService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void testDisplayOwner() throws Exception{
        when(oService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/123"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

    @Test
    void testInitCreationForm() throws Exception{
        mockMvc.perform(get("/owners/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(oService);
    }

    @Test
    void testProcessCreationForm() throws Exception{
        when(oService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/new"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));

        verify(oService).save(ArgumentMatchers.any());
    }

    @Test
    void testInitUpdateForm() throws Exception{
        when(oService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/edit"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));
    }

    @Test
    void testProcessUpdateForm() throws Exception{
        when(oService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/edit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));

        verify(oService).save(ArgumentMatchers.any());
    }

}
