package com.app.petclinic.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "types")
public class PetType extends BaseEntity{
    
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }

    
    
}
