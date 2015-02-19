package com.kadmandu.petme.web.entity;

import lombok.Getter;
import lombok.Setter;
/**
 * DTO data representation for animals
 * 
 * @author German Potes
 */
public class AnimalDTO {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
}
