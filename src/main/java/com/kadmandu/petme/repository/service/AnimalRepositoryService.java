package com.kadmandu.petme.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kadmandu.petme.repository.entity.AnimalRepository;

/**
 * Operations available to be performed at the persistence level for animals.
 * 
 * @author German Potes
 */
@Component
@Transactional
public class AnimalRepositoryService implements IAnimalRepositoryService {

    private final AnimalPersistence animalPersistence;

    /**
     * Service constructor
     *  
     * @param animalPersistence the persistence data access, injected by spring
     */
    @Autowired
    public AnimalRepositoryService(AnimalPersistence animalPersistence) {
        this.animalPersistence = animalPersistence;
    }

    @Override
    public List<AnimalRepository> getAll() {
        return animalPersistence.findAll();
    }

    @Override
    public AnimalRepository getOne(final String id) {
        return animalPersistence.findOne(id);
    }

    @Override
    public AnimalRepository create(final AnimalRepository entity) {
        return animalPersistence.save(entity);
    }

    @Override
    public AnimalRepository update(final AnimalRepository entity) {
        return animalPersistence.save(entity);
    }

    @Override
    public void delete(final AnimalRepository entity) {
        animalPersistence.delete(entity);
    }
}
