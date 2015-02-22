package com.kadmandu.petme.domain.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.web.entity.BreedDTO;

@Component
public class BreedDomainDTOTranslator implements Translator<Breed, BreedDTO> {

    private final AnimalDomainDTOTranslator animalTranslator;

    @Autowired
    public BreedDomainDTOTranslator(
            final AnimalDomainDTOTranslator animalTranslator) {
        this.animalTranslator = animalTranslator;
    }

    @Override
    public Breed translateFrom(BreedDTO breedDto) {
        final Breed breed = new Breed();
        breed.setId(breedDto.getId());
        breed.setName(breedDto.getName());
        breed.setAnimal(animalTranslator.translateFrom(breedDto.getAnimalDto()));

        return breed;
    }

    @Override
    public BreedDTO translateTo(Breed breed) {
        BreedDTO breedDto = new BreedDTO();
        breedDto.setId(breed.getId());
        breedDto.setName(breed.getName());
        breedDto.setAnimalDto(animalTranslator.translateTo(breed.getAnimal()));

        return breedDto;
    }

}
