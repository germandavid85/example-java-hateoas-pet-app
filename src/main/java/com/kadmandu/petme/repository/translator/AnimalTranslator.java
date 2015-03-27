package com.kadmandu.petme.repository.translator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
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

        Optional<List<BreedDTO>> optionalBreed = Optional.fromNullable(animalDto.getBreeds());
        animal.setBreeds(getTranslatedBreeds(optionalBreed.or(Collections.emptyList())));

        return animal;
    }

    @Override
    public AnimalDTO translateTo(final Animal animal)
    {
        final AnimalDTO animalDto = new AnimalDTO();
        animalDto.setAnimalId(animal.getAnimalId());
        animalDto.setName(animal.getName());

        Optional<List<Breed>> optionalBreed = Optional.fromNullable(animal.getBreeds());
        animalDto.setBreeds(getTranslatedBreedsDTO(optionalBreed.or(Collections.emptyList())));

        return animalDto;
    }

    private List<BreedDTO> getTranslatedBreedsDTO(final List<Breed> breeds)
    {
        List<BreedDTO> breedsRepo = new ArrayList<BreedDTO>(breeds.size());
        breeds.stream().forEach((breed) -> breedsRepo.add(breedTranslator.translateTo(breed)));
        return breedsRepo;
    }

    private List<Breed> getTranslatedBreeds(final List<BreedDTO> breeds)
    {
        List<Breed> breedsRepo = new ArrayList<Breed>(breeds.size());
        breeds.stream().forEach((breed) -> breedsRepo.add(breedTranslator.translateFrom(breed)));
        return breedsRepo;
    }

}
