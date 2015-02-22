package com.kadmandu.petme.domain.translator;

import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.repository.entity.AnimalRepository;

/**
 * Translator between {@link Animal} and {@link AnimalRepository}
 * 
 * @author German Potes
 */
@Component
public class AnimalDomainPersistenceTranslator implements
        Translator<Animal, AnimalRepository> {

    @Override
    public Animal translateFrom(final AnimalRepository animalRepository) {
        final Animal animal = new Animal();
        animal.setId(animalRepository.getId());
        animal.setName(animalRepository.getName());

        return animal;
    }

    @Override
    public AnimalRepository translateTo(final Animal animal) {
        final AnimalRepository animalRepository = new AnimalRepository();
        animalRepository.setId(animal.getId());
        animalRepository.setName(animal.getName());

        return animalRepository;
    }
}
