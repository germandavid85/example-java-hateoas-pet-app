package com.kadmandu.petme.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.domain.translator.AnimalDomainPersistenceTranslator;
import com.kadmandu.petme.repository.entity.AnimalRepository;
import com.kadmandu.petme.repository.service.IAnimalRepositoryService;

/**
 * Operations available to be performed at the domain level for animals.
 * 
 * @author German Potes
 */
@Component
public class AnimalDomainService implements IAnimalDomainService {

    private final IAnimalRepositoryService animalRepositoryService;
    private final AnimalDomainPersistenceTranslator animalTranslator;

    /**
     * Service constructor
     * 
     * @param animalRepository
     *            the persistence service, injected by spring
     * @param animalTranslator
     *            animal translator
     */
    @Autowired
    public AnimalDomainService(final IAnimalRepositoryService animalRepository,
            final AnimalDomainPersistenceTranslator animalTranslator) {
        this.animalRepositoryService = animalRepository;
        this.animalTranslator = animalTranslator;
    }

    @Override
    public List<Animal> getAll() {
        final List<Animal> animals = new ArrayList<Animal>(
                animalRepositoryService.getAll().size());
        for (AnimalRepository animalRepository : animalRepositoryService
                .getAll()) {
            animals.add(animalTranslator.translateFrom(animalRepository));
        }
        return animals;
    }

    @Override
    public Animal getOne(final String animalId) {
        final Animal animal = animalTranslator
                .translateFrom(animalRepositoryService.getOne(animalId));
        return animal;
    }

    @Override
    public Animal create(final Animal entity) {
        final AnimalRepository animalRepository = animalTranslator
                .translateTo(entity);
        final Animal animal = animalTranslator
                .translateFrom(animalRepositoryService.create(animalRepository));
        return animal;
    }

    @Override
    public Animal update(final Animal entity) {
        final AnimalRepository animalRepository = animalTranslator
                .translateTo(entity);
        final Animal animal = animalTranslator
                .translateFrom(animalRepositoryService.update(animalRepository));
        return animal;
    }

    @Override
    public void delete(final Animal entity) {
        final AnimalRepository animalRepository = animalTranslator
                .translateTo(entity);
        animalRepositoryService.delete(animalRepository);
    }

}
