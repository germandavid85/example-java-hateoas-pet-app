package com.kadmandu.petme.domain.translator;

import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.web.entity.AnimalDTO;

@Component
public class AnimalDomainDTOTranslator implements Translator<Animal, AnimalDTO> {

    @Override
    public Animal translateFrom(final AnimalDTO animalDto) {
        final Animal animal = new Animal();
        animal.setId(animalDto.getId());
        animal.setName(animalDto.getName());

        return animal;
    }

    @Override
    public AnimalDTO translateTo(final Animal animal) {
        final AnimalDTO animalDto = new AnimalDTO();
        animalDto.setId(animal.getId());
        animalDto.setName(animal.getName());

        return animalDto;
    }

}
