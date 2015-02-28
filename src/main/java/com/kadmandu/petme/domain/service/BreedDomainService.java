package com.kadmandu.petme.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.domain.translator.AnimalDomainPersistenceTranslator;
import com.kadmandu.petme.domain.translator.BreedDomainPersistenceTranslator;
import com.kadmandu.petme.repository.entity.BreedRepository;
import com.kadmandu.petme.repository.service.IBreedRepositoryService;

/**
 * Operations available to be performed at the domain level for breeds.
 * 
 * @author German Potes
 */
@Component
public class BreedDomainService implements IBreedDomainService {

    final IBreedRepositoryService breedRepositoryService;
    final BreedDomainPersistenceTranslator breedTranslator;
    final AnimalDomainPersistenceTranslator animalTranslator;

    /**
     * Service constructor
     * 
     * @param breedRepositoryService
     *            the persistence service, injected by spring
     * @param breedTranslator
     *            breed translator
     * @param animalTranslator
     *            animal translator
     */
    @Autowired
    public BreedDomainService(
            final IBreedRepositoryService breedRepositoryService,
            final BreedDomainPersistenceTranslator breedTranslator,
            final AnimalDomainPersistenceTranslator animalTranslator) {
        this.breedRepositoryService = breedRepositoryService;
        this.breedTranslator = breedTranslator;
        this.animalTranslator = animalTranslator;
    }

    @Override
    public List<Breed> getAll() {
        final List<BreedRepository> breedsRepo = breedRepositoryService
                .getAll();
        final List<Breed> breeds = new ArrayList<Breed>(breedsRepo.size());

        for (BreedRepository breedRepository : breedsRepo) {
            breeds.add(breedTranslator.translateFrom(breedRepository));
        }
        return breeds;
    }

    @Override
    public Breed getOne(final String id) {
        final Breed breed = breedTranslator
                .translateFrom(breedRepositoryService.getOne(id));
        return breed;
    }

    @Override
    public Breed create(final Breed entity) {
        final BreedRepository breedRepository = breedTranslator
                .translateTo(entity);
        final Breed breed = breedTranslator
                .translateFrom(breedRepositoryService.create(breedRepository));
        return breed;
    }

    @Override
    public Breed update(final Breed entity) {
        final BreedRepository breedRepository = breedTranslator
                .translateTo(entity);
        final Breed breed = breedTranslator
                .translateFrom(breedRepositoryService.update(breedRepository));
        return breed;
    }

    @Override
    public void delete(final Breed entity) {
        final BreedRepository breedRepository = breedTranslator
                .translateTo(entity);
        breedRepositoryService.delete(breedRepository);
    }
}
