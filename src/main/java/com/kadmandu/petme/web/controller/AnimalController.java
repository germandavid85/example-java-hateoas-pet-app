package com.kadmandu.petme.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.kadmandu.petme.web.resources.AnimalResource;
import com.kadmandu.petme.web.resources.AnimalResourceAssembler;
import com.kadmandu.petme.web.service.IAnimalWebService;

/**
 * Entry rest point for animals
 * 
 * @author German Potes
 */
@RestController
@Component
@RequestMapping(value = "/animals")
public class AnimalController {

    final private IAnimalWebService animalService;
    final private AnimalResourceAssembler resourceAssembler;

    @Autowired
    public AnimalController(final IAnimalWebService animalService,
            final AnimalResourceAssembler resourceAssembler) {
        this.animalService = animalService;
        this.resourceAssembler = resourceAssembler;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<List<AnimalResource>> getAllAnimals() {
        final List<AnimalDTO> animalsDto = animalService.getAll();
        final List<AnimalResource> animalResources = new ArrayList<>(animalsDto.size());

        animalResources.addAll(
            animalsDto.stream()
                .map(resourceAssembler::toResource)
                .collect(Collectors.toList()));

        return new ResponseEntity<>(animalResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{animalid}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<AnimalResource> getAnimal(
            @PathVariable(value = "animalid") final String animalId) {

        final AnimalDTO animalResponse = animalService.getOne(animalId);
        final AnimalResource resource = resourceAssembler
                .toResource(animalResponse);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<?> createAnimal(
            @RequestBody final AnimalDTO animalDto) {
        final AnimalDTO createdAnimal = animalService.create(animalDto);

        final String resourceUri = resourceAssembler.toResource(createdAnimal)
                .getLink("self").toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resourceUri));
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{animalid}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<AnimalResource> update(
            @RequestBody final AnimalDTO animalDTO) {
        final AnimalDTO animalResource = animalService.update(animalDTO);
        final AnimalResource resource = resourceAssembler
                .toResource(animalResource);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{animalid}", method = RequestMethod.GET, produces = "application/json")
//    public @ResponseBody ResponseEntity<AnimalResource> delete(
//            @RequestBody final AnimalDTO animalDto) {
//        animalService.delete(animalDto);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
