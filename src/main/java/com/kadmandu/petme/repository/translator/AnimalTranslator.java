package com.kadmandu.petme.repository.translator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.kadmandu.petme.repository.entity.Animal;
import com.kadmandu.petme.repository.entity.Breed;
import com.kadmandu.petme.web.entity.AnimalDTO;
import com.kadmandu.petme.web.entity.BreedDTO;

/**
 * Translator class for animal object between dto and persistence
 * 
 * @author German Potes
 */
@Component
public class AnimalTranslator implements Translator<Animal, AnimalDTO>
{
    @SuppressWarnings("PMD.SingularField")
    private final BreedTranslator breedTranslator;

    @Autowired
    public AnimalTranslator(final BreedTranslator breedTranslator)
    {
        this.breedTranslator = Preconditions.checkNotNull(breedTranslator);
    }

    @Override
    public Animal translateFrom(final AnimalDTO animalDto)
    {
        final Animal animal = new Animal();
        animal.setAnimalId(animalDto.getAnimalId());
        animal.setName(animalDto.getName());

        Optional<List<BreedDTO>> optionalBreed = Optional.ofNullable(animalDto.getBreeds());
        animal.setBreeds(getTranslatedBreeds(optionalBreed.orElse(Collections.emptyList())));

        return animal;
    }

    @Override
    public AnimalDTO translateTo(final Animal animal)
    {
        final AnimalDTO animalDto = new AnimalDTO();
        animalDto.setAnimalId(animal.getAnimalId());
        animalDto.setName(animal.getName());

        Optional<List<Breed>> optionalBreed = Optional.ofNullable(animal.getBreeds());
        animalDto.setBreeds(getTranslatedBreedsDTO(optionalBreed.orElse(Collections.emptyList())));

        return animalDto;
    }

    private List<BreedDTO> getTranslatedBreedsDTO(final List<Breed> breeds)
    {
        List<BreedDTO> breedsRepo = new ArrayList<>(breeds.size());
        breedsRepo.addAll(
            breeds.stream()
                .map(breedTranslator::translateTo)
                .collect(Collectors.toList()));
        return breedsRepo;
    }

    private List<Breed> getTranslatedBreeds(final List<BreedDTO> breeds)
    {
        List<Breed> breedsRepo = new ArrayList<>(breeds.size());
        breedsRepo.addAll(
            breeds.stream()
                .map(breedTranslator::translateFrom)
                .collect(Collectors.toList()));

        return breedsRepo;
    }

}
