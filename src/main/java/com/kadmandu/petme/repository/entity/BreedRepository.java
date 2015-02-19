package com.kadmandu.petme.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Getter;
import lombok.Setter;

/**
 * DAO with the data of breed.
 * 
 * @author German Potes
 */
public class BreedRepository {
    @Getter @Setter @Id
    private String id;
    @Getter @Setter
    private String name;
    @Getter @Setter @DBRef
    private AnimalRepository animal;

}
