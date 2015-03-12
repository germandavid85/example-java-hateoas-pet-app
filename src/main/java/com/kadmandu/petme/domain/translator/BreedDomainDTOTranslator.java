package com.kadmandu.petme.domain.translator;

import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.web.entity.BreedDTO;

@Component
public class BreedDomainDTOTranslator implements Translator<Breed, BreedDTO> {

    @Override
    public Breed translateFrom(final BreedDTO breedDto) {
        final Breed breed = new Breed();
        breed.setId(breedDto.getId());
        breed.setName(breedDto.getName());

        return breed;
    }

    @Override
    public BreedDTO translateTo(final Breed breed) {
        BreedDTO breedDto = new BreedDTO();
        breedDto.setId(breed.getId());
        breedDto.setName(breed.getName());

        return breedDto;
    }
}
