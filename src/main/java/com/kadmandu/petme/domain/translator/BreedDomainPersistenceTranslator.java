package com.kadmandu.petme.domain.translator;

import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.repository.entity.BreedRepository;

/**
 * Translator between {@link Breed} and {@link BreedRepository}
 * 
 * @author German Potes
 */
@Component
public class BreedDomainPersistenceTranslator implements
        Translator<Breed, BreedRepository> {

    @Override
    public Breed translateFrom(BreedRepository breedRepository) {
        final Breed breed = new Breed();
        breed.setId(breedRepository.getId());
        breed.setName(breedRepository.getName());

        return breed;
    }

    @Override
    public BreedRepository translateTo(Breed breed) {
        BreedRepository breedRepository = new BreedRepository();
        breedRepository.setId(breed.getId());
        breedRepository.setName(breed.getName());

        return breedRepository;
    }

}
