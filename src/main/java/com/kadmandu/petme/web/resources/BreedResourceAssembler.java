package com.kadmandu.petme.web.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import lombok.Setter;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.kadmandu.petme.web.controller.BreedController;
import com.kadmandu.petme.web.entity.BreedDTO;

@Component
public class BreedResourceAssembler implements ResourceAssembler<BreedDTO, BreedResource>
{

    @Setter
    private String animalId;

    @Override
    public BreedResource toResource(final BreedDTO breedDto)
    {
        Preconditions.checkNotNull(animalId);

        final BreedResource breedResource = new BreedResource(breedDto);
        breedResource.add(linkTo(
            methodOn(BreedController.class).getBreed(animalId, breedDto.getBreedId()))
            .withSelfRel());
        return breedResource;
    }

}
