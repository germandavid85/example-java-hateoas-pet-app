package com.kadmandu.petme.web.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO data representation for breeds
 * 
 * @author German Potes
 */
public class BreedDTO {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private AnimalDTO animalDto;
}
