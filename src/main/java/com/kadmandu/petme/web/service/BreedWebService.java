package com.kadmandu.petme.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.kadmandu.petme.repository.entity.Animal;
import com.kadmandu.petme.repository.entity.Breed;
import com.kadmandu.petme.repository.service.IBreedRepositoryService;
import com.kadmandu.petme.repository.translator.Translator;
import com.kadmandu.petme.web.entity.AnimalDTO;
import com.kadmandu.petme.web.entity.BreedDTO;

/**
 * Service to handle the actions between the web and domain layer for breeds
 * 
 * @author German Potes
 */
@Component
public class BreedWebService implements IBreedWebService
{
    private final IBreedRepositoryService breedRepositoryService;
    private final Translator<Breed, BreedDTO> breedTranslator;
    private final Translator<Animal, AnimalDTO> animalTranslator;

    @Autowired
    public BreedWebService(final IBreedRepositoryService breedRepositoryService,
        final Translator<Breed, BreedDTO> breedTranslator,
        final Translator<Animal, AnimalDTO> animalTranslator)
    {
        this.breedRepositoryService = Preconditions.checkNotNull(breedRepositoryService,
            "breedRepositoryService must be not null");
        this.breedTranslator = Preconditions.checkNotNull(breedTranslator,
            "breedTranslator must be not null");
        this.animalTranslator = Preconditions.checkNotNull(animalTranslator,
            "animalTranslator must be not null");
    }

    @Override
    public List<BreedDTO> getAll()
    {
        List<Breed> breedsDomain = breedRepositoryService.getAll();
        final List<BreedDTO> breeds = new ArrayList<>(breedsDomain.size());

        breeds.addAll(
            breedsDomain.stream()
                .map(breedTranslator::translateTo)
                .collect(Collectors.toList()));

        return breeds;
    }

    @Override
    public BreedDTO getOne(final String breedId)
    {
        return breedTranslator.translateTo(breedRepositoryService.getOne(breedId));
    }

    @Override
    public BreedDTO create(final BreedDTO entity)
    {
        final Breed breed = breedTranslator.translateFrom(entity);
        return breedTranslator.translateTo(breedRepositoryService.create(breed));
    }

    @Override
    public BreedDTO update(final BreedDTO entity)
    {
        final Breed breed = breedTranslator.translateFrom(entity);
        return breedTranslator.translateTo(breedRepositoryService.update(breed));
    }

    @Override
    public void delete(final BreedDTO entity)
    {
        final Breed breed = breedTranslator.translateFrom(entity);
        breedRepositoryService.delete(breed);
    }

    @Override
    public AnimalDTO getOne(final String animalId, final String breedId)
    {
        return animalTranslator.translateTo(breedRepositoryService.getOne(animalId, breedId));
    }
}
