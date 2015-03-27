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
    private String breedId;
    @Getter @Setter
    private String name;
}
