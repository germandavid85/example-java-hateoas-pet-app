package com.kadmandu.petme.domain.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain data representation for breeds
 * 
 * @author German Potes
 */
public class Breed {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Animal animal;

}
