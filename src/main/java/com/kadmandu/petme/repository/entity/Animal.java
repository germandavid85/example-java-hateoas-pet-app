package com.kadmandu.petme.repository.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DAO with the data of animal.
 * 
 * @author German Potes
 */
@Document
public class Animal {
    @Getter
    @Setter
    @Id
    private String animalId;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private List<Breed> breeds;
}
