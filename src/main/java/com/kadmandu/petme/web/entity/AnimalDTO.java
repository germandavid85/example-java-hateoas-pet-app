package com.kadmandu.petme.web.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * DTO data representation for animals
 * 
 * @author German Potes
 */
public class AnimalDTO {
    @Getter @Setter
    private String animalId;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private List<BreedDTO> breeds;
}
