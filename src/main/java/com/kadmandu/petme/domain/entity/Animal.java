package com.kadmandu.petme.domain.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Domain data representation for animals
 * 
 * @author German Potes
 */
public class Animal {
    @Getter
    @Setter
    @NonNull
    private String id;
    @Getter
    @Setter
    @NonNull
    private String name;
}
