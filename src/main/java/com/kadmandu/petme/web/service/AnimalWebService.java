package com.kadmandu.petme.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.domain.service.IAnimalDomainService;
import com.kadmandu.petme.domain.translator.Translator;
import com.kadmandu.petme.web.entity.AnimalDTO;

@Component
public class AnimalWebService implements IAnimalWebService {

    final IAnimalDomainService animalDomainService;
    final Translator<Animal, AnimalDTO> animalTranslator;

    @Autowired
    public AnimalWebService(
        final IAnimalDomainService animalDomainService,
        final Translator<Animal, AnimalDTO> animalTranslator) {
        super();
        this.animalDomainService = animalDomainService;
        this.animalTranslator = animalTranslator;
    }

    @Override
    public List<AnimalDTO> getAll() {
        final List<AnimalDTO> animals = new ArrayList<AnimalDTO>(animalDomainService.getAll().size());
        for (Animal animal : animalDomainService.getAll()) {
            animals.add(animalTranslator.translateTo(animal));
        }
        return animals;
    }

    @Override
    public AnimalDTO getOne(final String id) {
        final AnimalDTO animalDto = animalTranslator.translateTo(animalDomainService.getOne(id));
        return animalDto;
    }

    @Override
    public AnimalDTO create(final AnimalDTO entity) {
        final Animal animal = animalTranslator.translateFrom(entity);
        final AnimalDTO animalDto = 
                animalTranslator.translateTo(animalDomainService.create(animal));
        return animalDto;
    }

    @Override
    public AnimalDTO update(final AnimalDTO entity) {
        final Animal animal = animalTranslator.translateFrom(entity);
        final AnimalDTO animalDto = 
            animalTranslator.translateTo(animalDomainService.create(animal));
        return animalDto;
    }

    @Override
    public void delete(final AnimalDTO entity) {
        final Animal animal = animalTranslator.translateFrom(entity);
        animalDomainService.delete(animal);
    }

}
