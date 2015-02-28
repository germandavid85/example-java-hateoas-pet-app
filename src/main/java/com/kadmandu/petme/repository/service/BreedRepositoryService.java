package com.kadmandu.petme.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kadmandu.petme.repository.entity.BreedRepository;

/**
 * Operations available to be performed at the persistence level for breeds.
 * 
 * @author German Potes
 */
@Component
@Transactional
public class BreedRepositoryService implements IBreedRepositoryService {

    private final BreedPersistence breedPersistence;

    /**
     * Service constructor
     *  
     * @param breedPersistence the persistence data access, injected by spring
     */
    @Autowired
    public BreedRepositoryService(final BreedPersistence breedPersistence) {
        this.breedPersistence = breedPersistence;
    }

    @Override
    public List<BreedRepository> getAll() {
        return breedPersistence.findAll();
    }

    @Override
    public BreedRepository getOne(final String id) {
        return breedPersistence.findOne(id);
    }

    @Override
    public BreedRepository create(final BreedRepository entity) {
        return breedPersistence.save(entity);
    }

    @Override
    public BreedRepository update(final BreedRepository entity) {
        return breedPersistence.save(entity);
    }

    @Override
    public void delete(final BreedRepository entity) {
        breedPersistence.delete(entity);
    }

}
