package com.kadmandu.petme.web.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.kadmandu.petme.web.controller.AnimalController;
import com.kadmandu.petme.web.entity.AnimalDTO;

@Component
public class AnimalResourceAssembler implements ResourceAssembler<AnimalDTO, AnimalResource>
{

    @Override
    public AnimalResource toResource(final AnimalDTO animalDto)
    {
        final AnimalResource animalResource = new AnimalResource(animalDto);
        animalResource.add(linkTo(
            methodOn(AnimalController.class).getAnimal(animalDto.getAnimalId())).withSelfRel());
        return animalResource;
    }

}
