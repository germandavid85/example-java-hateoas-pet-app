package com.kadmandu.petme.web.resources;

import org.springframework.hateoas.Resource;

import com.kadmandu.petme.web.entity.AnimalDTO;

public class AnimalResource extends Resource<AnimalDTO> {

    AnimalResource(final AnimalDTO content) {
        super(content);
    }
}
