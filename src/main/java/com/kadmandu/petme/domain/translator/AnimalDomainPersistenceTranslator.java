package com.kadmandu.petme.domain.translator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.repository.entity.AnimalRepository;
import com.kadmandu.petme.repository.entity.BreedRepository;

/**
 * Translator between {@link Animal} and {@link AnimalRepository}
 * 
 * @author German Potes
 */
@Component
public class AnimalDomainPersistenceTranslator implements
        Translator<Animal, AnimalRepository> {

    private final BreedDomainPersistenceTranslator breedTranslator;

    @Autowired
    public AnimalDomainPersistenceTranslator(
            final BreedDomainPersistenceTranslator breedTranslator) {

        this.breedTranslator = Preconditions.checkNotNull(breedTranslator);
    }

    @Override
    public Animal translateFrom(final AnimalRepository animalRepository) {
        final Animal animal = new Animal();
        animal.setId(animalRepository.getId());
        animal.setName(animalRepository.getName());

        Optional<List<BreedRepository>> optionalBreed = Optional
                .fromNullable(animalRepository.getBreeds());
        animal.setBreeds(getTranslatedBreeds(optionalBreed.or(Collections
                .emptyList())));

        return animal;
    }

    @Override
    public AnimalRepository translateTo(final Animal animal) {
        final AnimalRepository animalRepository = new AnimalRepository();
        animalRepository.setId(animal.getId());
        animalRepository.setName(animal.getName());

        Optional<List<Breed>> optionalBreed = Optional.fromNullable(animal
                .getBreeds());
        animalRepository.setBreeds(getTranslatedBreedsRepository(optionalBreed
                .or(Collections.emptyList())));

        return animalRepository;
    }

    private List<BreedRepository> getTranslatedBreedsRepository(
            final List<Breed> breeds) {
        List<BreedRepository> breedsRepo = new ArrayList<BreedRepository>(
                breeds.size());
        breeds.stream().forEach(
                (breed) -> breedsRepo.add(breedTranslator.translateTo(breed)));
        return breedsRepo;
    }

    private List<Breed> getTranslatedBreeds(final List<BreedRepository> breeds) {
        List<Breed> breedsRepo = new ArrayList<Breed>(breeds.size());
        breeds.stream()
                .forEach(
                        (breed) -> breedsRepo.add(breedTranslator
                                .translateFrom(breed)));
        return breedsRepo;
    }
}
