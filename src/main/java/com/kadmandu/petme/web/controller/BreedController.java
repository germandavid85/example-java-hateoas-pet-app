package com.kadmandu.petme.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kadmandu.petme.web.entity.AnimalDTO;
import com.kadmandu.petme.web.entity.BreedDTO;
import com.kadmandu.petme.web.resources.BreedResource;
import com.kadmandu.petme.web.resources.BreedResourceAssembler;
import com.kadmandu.petme.web.service.IAnimalWebService;
import com.kadmandu.petme.web.service.IBreedWebService;

/**
 * Entry rest point for animals
 * 
 * @author German Potes
 */
@RestController
@Component
@RequestMapping(value = "/animals/{animalid}/breeds")
public class BreedController {

    final private IBreedWebService breedService;
    final private IAnimalWebService animalService;
    final private BreedResourceAssembler resourceAssembler;

    @Autowired
    public BreedController(final IBreedWebService breedService,
            final BreedResourceAssembler resourceAssembler,
            final IAnimalWebService animalService) {
        this.breedService = breedService;
        this.resourceAssembler = resourceAssembler;
        this.animalService = animalService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<List<BreedResource>> getAllBreeds(
            @PathVariable(value = "animalid") final String animalId) {
        List<BreedDTO> breedsDto = animalService.getOne(animalId).getBreeds();
        List<BreedResource> breedResources = new ArrayList<BreedResource>(
                breedsDto.size());

        resourceAssembler.setAnimalId(animalId);
        breedsDto.stream().forEach(
                (breed) -> breedResources.add(resourceAssembler
                        .toResource(breed)));

        return new ResponseEntity<List<BreedResource>>(breedResources,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{breedid}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<BreedResource> getBreed(
            @PathVariable(value = "animalid") final String animalId,
            @PathVariable(value = "breedid") final String breedId) {
        BreedDTO animalResponse = breedService.getOne(breedId);
        resourceAssembler.setAnimalId(animalId);
        BreedResource resource = resourceAssembler.toResource(animalResponse);

        return new ResponseEntity<BreedResource>(resource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<?> createBreed(
            @PathVariable(value = "animalid") final String animalId,
            @RequestBody final BreedDTO breedDto) {
        AnimalDTO animalDto = new AnimalDTO();
        animalDto.setId(animalId);
        BreedDTO createdBreed = breedService.create(breedDto);

        resourceAssembler.setAnimalId(animalId);
        String resourceUri = resourceAssembler.toResource(createdBreed)
                .getLink("self").toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resourceUri));
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }
}
