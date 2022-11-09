package com.app.petclinic.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import com.app.petclinic.model.Owner;
import com.app.petclinic.model.Pet;
import com.app.petclinic.model.PetType;
import com.app.petclinic.services.PetService;
import com.app.petclinic.services.VisitService;

@ExtendWith(MockitoExtension.class)
public class visitControllerTest {

    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";
    private final UriTemplate visiUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitUri;
    private MockMvc mockMvc;

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    visitController vController;

    @BeforeEach
    void setUp() throws Exception {
        Long petId = 1L;
        Long ownerId = 1L;
        when(petService.findById(anyLong())).thenReturn(
                Pet.builder().id(petId).name("Cutie").visits(new HashSet<>())
                        .owner(Owner.builder().id(ownerId).firstName("John").lastName("Doe").build())
                        .petType(PetType.builder().name("Dog").build()).build());
        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitUri = visiUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders.standaloneSetup(vController).build();
    }

    @Test
    void testInitNewVisitForm() throws Exception{
        mockMvc.perform(get(visitUri)).andExpect(status().isOk()).andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void testProccessNewVisitForm() throws Exception{
        mockMvc.perform(post(visitUri)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            //.param("date","2018-11-11")
                            .param("description", YET_ANOTHER_VISIT_DESCRIPTION))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS_1))
                .andExpect(model().attributeExists("visit"))
        ;
    }
}

