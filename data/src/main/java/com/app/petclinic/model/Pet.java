package com.app.petclinic.model;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        
        if (visits == null || visits.size() > 0) {
            this.visits = visits;
        }
    }

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Owner owner;


    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();
    
}
