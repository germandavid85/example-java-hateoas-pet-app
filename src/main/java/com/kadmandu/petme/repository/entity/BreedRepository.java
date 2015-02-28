package com.kadmandu.petme.repository.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * DAO with the data of breed.
 * 
 * @author German Potes
 */
public class BreedRepository {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
}
