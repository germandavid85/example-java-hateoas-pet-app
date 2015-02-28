package com.kadmandu.petme.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.domain.service.IBreedDomainService;
import com.kadmandu.petme.domain.translator.Translator;
import com.kadmandu.petme.web.entity.AnimalDTO;
import com.kadmandu.petme.web.entity.BreedDTO;

/**
 * Service to handle the actions between the web and domain layer for breeds
 * 
 * @author German Potes
 */
@Component
public class BreedWebService implements IBreedWebService {

    final private IBreedDomainService breedDomainService;
    final private Translator<Breed, BreedDTO> breedTranslator;
    final private Translator<Animal, AnimalDTO> animalTranslator;

    @Autowired
    public BreedWebService(final IBreedDomainService breedDomainService,
            final Translator<Breed, BreedDTO> breedTranslator,
            final Translator<Animal, AnimalDTO> animalTranslator) {
        this.breedDomainService = breedDomainService;
        this.breedTranslator = breedTranslator;
        this.animalTranslator = animalTranslator;
    }

    @Override
    public List<BreedDTO> getAll() {
        List<Breed> breedsDomain = breedDomainService.getAll();
        final List<BreedDTO> breeds = new ArrayList<BreedDTO>(
                breedsDomain.size());

        breedsDomain.stream().forEach(
                (breed) -> breeds.add(breedTranslator.translateTo(breed)));

        return breeds;
    }

    @Override
    public BreedDTO getOne(final String id) {
        final BreedDTO breed = breedTranslator.translateTo(breedDomainService
                .getOne(id));
        return breed;
    }

    @Override
    public BreedDTO create(final BreedDTO entity) {
        final Breed breed = breedTranslator.translateFrom(entity);
        final BreedDTO breedDto = breedTranslator
                .translateTo(breedDomainService.create(breed));
        return breedDto;
    }

    @Override
    public BreedDTO update(final BreedDTO entity) {
        final Breed breed = breedTranslator.translateFrom(entity);
        final BreedDTO breedDto = breedTranslator
                .translateTo(breedDomainService.update(breed));
        return breedDto;
    }

    @Override
    public void delete(final BreedDTO entity) {
        final Breed breed = breedTranslator.translateFrom(entity);
        breedDomainService.delete(breed);
    }

    @Override
    public List<BreedDTO> getByAnimal(final AnimalDTO animalDto) {
        final Animal animalDomain = animalTranslator.translateFrom(animalDto);
        List<Breed> breedsDomain = breedDomainService.getByAnimal(animalDomain);
        final List<BreedDTO> breeds = new ArrayList<BreedDTO>(
                breedsDomain.size());

        breedsDomain.stream().forEach(
                (breed) -> breeds.add(breedTranslator.translateTo(breed)));

        return breeds;
    }

}
