package com.kadmandu.petme.web.resources;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.kadmandu.petme.web.entity.BreedDTO;

@Component
public class BreedResourceAssembler implements ResourceAssembler<BreedDTO, BreedResource> {

    @Override
    public BreedResource toResource(final BreedDTO breedDto) {
        final BreedResource breedResource = new BreedResource(breedDto);
//        stateResource.add(
//            linkTo(methodOn(StateController.class).getState(
//                stateDto.getCountryDto().getId(), stateDto.getId())).withSelfRel());
//        stateResource.add(
//            linkTo(methodOn(CountryController.class).getCountry(
//                stateDto.getCountryDto().getId())).withRel(StateResource.STATE_COUNTRY));
//        return stateResource;
        return breedResource;
    }

}
