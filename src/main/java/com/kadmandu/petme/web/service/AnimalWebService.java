package com.kadmandu.petme.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.kadmandu.petme.repository.entity.Animal;
import com.kadmandu.petme.repository.service.IAnimalRepositoryService;
import com.kadmandu.petme.repository.translator.Translator;
import com.kadmandu.petme.web.entity.AnimalDTO;

/**
 * Service to handle the actions between the web and persistence layer for animals
 * 
 * @author German Potes
 */
@Component
public class AnimalWebService implements IAnimalWebService
{
    final private IAnimalRepositoryService animalRepositoryService;
    final private Translator<Animal, AnimalDTO> animalTranslator;

    @Autowired
    public AnimalWebService(final IAnimalRepositoryService animalRepositoryService,
        final Translator<Animal, AnimalDTO> animalTranslator)
    {
        this.animalRepositoryService = Preconditions.checkNotNull(animalRepositoryService,
            "animalRepositoryService must be not null");
        this.animalTranslator = Preconditions.checkNotNull(animalTranslator,
            "animalTranslator must be not null");
    }

    @Override
    public List<AnimalDTO> getAll()
    {
        final List<AnimalDTO> animals = new ArrayList<AnimalDTO>(animalRepositoryService.getAll()
            .size());
        animalRepositoryService.getAll().stream()
            .forEach((animal) -> animals.add(animalTranslator.translateTo(animal)));

        return animals;
    }

    @Override
    public AnimalDTO getOne(final String id)
    {
        final AnimalDTO animalDto = animalTranslator
            .translateTo(animalRepositoryService.getOne(id));
        return animalDto;
    }

    @Override
    public AnimalDTO create(final AnimalDTO entity)
    {
        final Animal animal = animalTranslator.translateFrom(entity);
        final AnimalDTO animalDto = animalTranslator.translateTo(animalRepositoryService
            .create(animal));
        return animalDto;
    }

    @Override
    public AnimalDTO update(final AnimalDTO entity)
    {
        final Animal animal = animalTranslator.translateFrom(entity);
        final AnimalDTO animalDto = animalTranslator.translateTo(animalRepositoryService
            .create(animal));
        return animalDto;
    }

    @Override
    public void delete(final AnimalDTO entity)
    {
        final Animal animal = animalTranslator.translateFrom(entity);
        animalRepositoryService.delete(animal);
    }
}
