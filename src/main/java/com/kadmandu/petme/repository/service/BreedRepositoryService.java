package com.kadmandu.petme.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kadmandu.petme.repository.entity.Animal;
import com.kadmandu.petme.repository.entity.Breed;

/**
 * Operations available to be performed at the persistence level for breeds.
 * 
 * @author German Potes
 */
@Component
@Transactional
public class BreedRepositoryService implements IBreedRepositoryService {

    private final BreedRepository breedPersistence;
    private final AnimalRepository animalPersistence;

    /**
     * Service constructor
     *  
     * @param breedPersistence the persistence data access, injected by spring
     */
    @Autowired
    public BreedRepositoryService(final BreedRepository breedPersistence,
            final AnimalRepository animalPersistence) {
        this.breedPersistence = breedPersistence;
        this.animalPersistence = animalPersistence;
    }

    @Override
    public List<Breed> getAll() {
        return breedPersistence.findAll();
    }

    @Override
    public Breed getOne(final String breedId) {
        return breedPersistence.findOne(breedId);
    }

    @Override
    public Breed create(final Breed entity) {
        return breedPersistence.save(entity);
    }

    @Override
    public Breed update(final Breed entity) {
        return breedPersistence.save(entity);
    }

    @Override
    public void delete(final Breed entity) {
        breedPersistence.delete(entity);
    }

    @Override
    public Animal getOne(String animalId, String breedId) {
        return animalPersistence.getBreedsCustomMethod(animalId, breedId);
    }

}
