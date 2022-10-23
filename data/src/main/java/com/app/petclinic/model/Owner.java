package com.app.petclinic.model;

import java.util.*;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person{

	@Builder
	public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
		super(id, firstName, lastName);
		this.address = address;
		this.city = city;
		this.telephone = telephone;

        if(pets != null) {
            this.pets = pets;
        }
	}
	
    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephones")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
    

    //return the pet with given name or null if none were found for this owner
    //return true if pet name is aleardy used
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    //return the pet with given name or null if none were found for this owner
    //return true if pet name is aleardy used
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();

                if(compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }
}
