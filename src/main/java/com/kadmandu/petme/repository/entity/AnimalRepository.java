package com.kadmandu.petme.repository.entity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
/**
 * DAO with the data of animal.
 * 
 * @author German Potes
 */
public class AnimalRepository{
    @Getter @Setter @Id
    private String id;
    @Getter @Setter
    private String name;
}
