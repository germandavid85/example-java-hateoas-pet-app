package com.kadmandu.petme.web.resources;

import org.springframework.hateoas.Resource;

import com.kadmandu.petme.web.entity.BreedDTO;

public class BreedResource extends Resource<BreedDTO> {

    public BreedResource(final BreedDTO content) {
        super(content);
    }

}
