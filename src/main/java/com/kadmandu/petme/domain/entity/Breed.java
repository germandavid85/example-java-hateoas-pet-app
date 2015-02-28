package com.kadmandu.petme.domain.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Domain data representation for breeds
 * 
 * @author German Potes
 */
public class Breed {
    @Getter
    @Setter
    @NonNull
    private String id;
    @Getter
    @Setter
    @NonNull
    private String name;

}
