package com.kadmandu.petme.repository.translator;

import org.springframework.stereotype.Component;

import com.kadmandu.petme.repository.entity.Breed;
import com.kadmandu.petme.web.entity.BreedDTO;

/**
 * Translator class for breed object between dto and persistence
 * 
 * @author German Potes
 */
@Component
public class BreedTranslator implements Translator<Breed, BreedDTO>
{

    @Override
    public Breed translateFrom(final BreedDTO breedDto)
    {
        final Breed breed = new Breed();
        breed.setBreedId(breedDto.getBreedId());
        breed.setName(breedDto.getName());

        return breed;
    }

    @Override
    public BreedDTO translateTo(final Breed breed)
    {
        BreedDTO breedDto = new BreedDTO();
        breedDto.setBreedId(breed.getBreedId());
        breedDto.setName(breed.getName());

        return breedDto;
    }
}
