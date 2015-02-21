package com.kadmandu.petme.domain.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain data representation for animals
 * 
 * @author German Potes
 */
public class Animal {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
}
