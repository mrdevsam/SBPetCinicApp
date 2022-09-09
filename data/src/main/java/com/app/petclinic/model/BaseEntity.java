package com.app.petclinic.model;


import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
