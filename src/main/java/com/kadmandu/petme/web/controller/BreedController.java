package com.kadmandu.petme.web.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kadmandu.petme.web.entity.AnimalDTO;
import com.kadmandu.petme.web.entity.BreedDTO;
import com.kadmandu.petme.web.resources.BreedResourceAssembler;
import com.kadmandu.petme.web.service.IBreedWebService;

/**
 * Entry rest point for animals
 * 
 * @author German Potes
 */
@RestController
@Component
@RequestMapping(value = "/animals/{animalid}/breed/")
public class BreedController {

    final private IBreedWebService breedService;
    final private BreedResourceAssembler resourceAssembler;

    @Autowired
    public BreedController(final IBreedWebService breedService,
            final BreedResourceAssembler resourceAssembler) {
        super();
        this.breedService = breedService;
        this.resourceAssembler = resourceAssembler;
    }

//    public @ResponseBody ResponseEntity<List<BreedResource>> getAllBreeds(
//            @PathVariable(value = "animalid") final String animalId) {
//        final AnimalDTO animalDto = new AnimalDTO();
//        animalDto.setId(animalId);
//        final List<BreedDTO> breedsDto = breedService.getByAnimal(animalDto);
//        final List<BreedResource> breedResources = new ArrayList<BreedResource>(
//                breedsDto.size());
//
//        breedsDto.stream().forEach(
//                (breed) -> breedResources.add(resourceAssembler
//                        .toResource(breed)));
//
//        return new ResponseEntity<List<BreedResource>>(breedResources,
//                HttpStatus.OK);
//    }

    public @ResponseBody ResponseEntity<?> createBreed(
            @PathVariable(value = "animalid") final String animalId,
            @RequestBody final BreedDTO breedDto) {
        final AnimalDTO animalDto = new AnimalDTO();
        animalDto.setId(animalId);
        final BreedDTO createdBreed = breedService.create(breedDto);

        final String resourceUri = resourceAssembler.toResource(createdBreed)
                .getLink("self").toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resourceUri));
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }
}
