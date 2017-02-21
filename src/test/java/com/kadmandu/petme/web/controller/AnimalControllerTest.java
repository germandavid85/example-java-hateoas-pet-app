package com.kadmandu.petme.web.controller;

import com.kadmandu.petme.repository.entity.Animal;
import com.kadmandu.petme.repository.service.AnimalRepository;
import com.kadmandu.petme.repository.service.AnimalRepositoryService;
import com.kadmandu.petme.repository.service.IAnimalRepositoryService;
import com.kadmandu.petme.repository.translator.AnimalTranslator;
import com.kadmandu.petme.repository.translator.BreedTranslator;
import com.kadmandu.petme.web.entity.AnimalDTO;
import com.kadmandu.petme.web.resources.AnimalResource;
import com.kadmandu.petme.web.resources.AnimalResourceAssembler;
import com.kadmandu.petme.web.service.AnimalWebService;
import com.kadmandu.petme.web.service.IAnimalWebService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AnimalControllerTest {
    private static final String ANIMAL_ID = "a123";
    private static final String ANIMAL_NAME = "Dog";

    @Mock
    private AnimalRepository mockAnimalRepository = mock(AnimalRepository.class);
    private AnimalController animalController;

    private Animal animal;

    @Before
    public void setup() {
        setupMockServlet();

        IAnimalRepositoryService animalRepositoryService = new AnimalRepositoryService(mockAnimalRepository);
        setupAnimal();
        when(animalRepositoryService.getAll()).thenReturn(Collections.singletonList(animal));

        BreedTranslator breedTranslator = new BreedTranslator();
        AnimalTranslator animalTranslator = new AnimalTranslator(breedTranslator);

        IAnimalWebService animalWebService = new AnimalWebService(animalRepositoryService, animalTranslator);
        AnimalResourceAssembler animalResourceAssembler = new AnimalResourceAssembler();
        animalController = new AnimalController(animalWebService, animalResourceAssembler);
    }

    @Test
    public void whenIGetAllAnimalsThenIShouldGetAll() {
        ResponseEntity<List<AnimalResource>> allAnimals = animalController.getAllAnimals();

        List<AnimalResource> animalsResource = allAnimals.getBody();

        assertNotNull("Incorrect list of animals", animalsResource);
        assertEquals("Incorrect number of animals", 1, animalsResource.size());

        AnimalDTO animal = animalsResource.get(0).getContent();

        assertEquals("Incorrect animal Id", ANIMAL_ID, animal.getAnimalId());
        assertEquals("Incorrect animal name", ANIMAL_NAME, animal.getName());
    }

    private void setupMockServlet() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);
    }

    private void setupAnimal() {
        animal = new Animal();
        animal.setAnimalId(ANIMAL_ID);
        animal.setName(ANIMAL_NAME);
    }
}
