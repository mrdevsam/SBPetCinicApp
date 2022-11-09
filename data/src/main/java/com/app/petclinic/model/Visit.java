package com.app.petclinic.model;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity{

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    
}
