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
import com.kadmandu.petme.web.entity.AnimalDTO;
import com.kadmandu.petme.web.entity.BreedDTO;

@Component
public class AnimalDomainDTOTranslator implements Translator<Animal, AnimalDTO> {

    private final BreedDomainDTOTranslator breedTranslator;

    @Autowired
    public AnimalDomainDTOTranslator(
            final BreedDomainDTOTranslator breedTranslator) {
        this.breedTranslator = Preconditions.checkNotNull(breedTranslator);
    }

    @Override
    public Animal translateFrom(final AnimalDTO animalDto) {
        final Animal animal = new Animal();
        animal.setId(animalDto.getId());
        animal.setName(animalDto.getName());

        Optional<List<BreedDTO>> optionalBreed = Optional
                .fromNullable(animalDto.getBreeds());
        animal.setBreeds(getTranslatedBreeds(optionalBreed.or(Collections
                .emptyList())));

        return animal;
    }

    @Override
    public AnimalDTO translateTo(final Animal animal) {
        final AnimalDTO animalDto = new AnimalDTO();
        animalDto.setId(animal.getId());
        animalDto.setName(animal.getName());

        Optional<List<Breed>> optionalBreed = Optional.fromNullable(animal
                .getBreeds());
        animalDto.setBreeds(getTranslatedBreedsDTO(optionalBreed.or(Collections
                .emptyList())));

        return animalDto;
    }

    private List<BreedDTO> getTranslatedBreedsDTO(final List<Breed> breeds) {
        List<BreedDTO> breedsRepo = new ArrayList<BreedDTO>(breeds.size());
        breeds.stream().forEach(
                (breed) -> breedsRepo.add(breedTranslator.translateTo(breed)));
        return breedsRepo;
    }

    private List<Breed> getTranslatedBreeds(final List<BreedDTO> breeds) {
        List<Breed> breedsRepo = new ArrayList<Breed>(breeds.size());
        breeds.stream()
                .forEach(
                        (breed) -> breedsRepo.add(breedTranslator
                                .translateFrom(breed)));
        return breedsRepo;
    }

}
