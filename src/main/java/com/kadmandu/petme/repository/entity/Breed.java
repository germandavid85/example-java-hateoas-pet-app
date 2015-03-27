package com.kadmandu.petme.repository.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * DAO with the data of breed.
 * 
 * @author German Potes
 */
public class Breed {
    @Getter @Setter
    private String breedId;
    @Getter @Setter
    private String name;
}
