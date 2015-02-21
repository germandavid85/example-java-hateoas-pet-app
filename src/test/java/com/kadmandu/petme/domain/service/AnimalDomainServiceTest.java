package com.kadmandu.petme.domain.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.domain.translator.AnimalDomainPersistenceTranslator;
import com.kadmandu.petme.repository.entity.AnimalRepository;
import com.kadmandu.petme.repository.service.IAnimalRepositoryService;

@RunWith(MockitoJUnitRunner.class)
public class AnimalDomainServiceTest {

    private final static String ANIMAL_ID = "an123";
    private final static String ANIMAL_NAME = "Dog";

    @Mock
    private IAnimalRepositoryService mockAnimalRepoService;
    @Captor
    private ArgumentCaptor<AnimalRepository> animalCaptor;

    private AnimalDomainPersistenceTranslator animalDomPerTranslator;
    private AnimalRepository animalRepo;

    private AnimalDomainService testService;

    @Before
    public void setUp() {
        animalDomPerTranslator = new AnimalDomainPersistenceTranslator();
        testService = new AnimalDomainService(mockAnimalRepoService,
                animalDomPerTranslator);
        animalRepo = new AnimalRepository();
        animalRepo.setId(ANIMAL_ID);
        animalRepo.setName(ANIMAL_NAME);
    }

    @Test
    public void testGetAll() {
        when(mockAnimalRepoService.getAll()).thenReturn(
                Collections.singletonList(animalRepo));
        List<Animal> animals = testService.getAll();

        assertThat("The size of animals", animals.size(), is(1));
        assertThat("The animal id", animals.get(0).getId(), is(ANIMAL_ID));
        assertThat("The name of the animal", animals.get(0).getName(),
                is(ANIMAL_NAME));
    }

    @Test
    public void testGetOne() {
        when(mockAnimalRepoService.getOne(ANIMAL_ID)).thenReturn(animalRepo);

        Animal animal = testService.getOne(ANIMAL_ID);
        assertThat("The animal id", animal.getId(), is(ANIMAL_ID));
        assertThat("The name of the animal", animal.getName(), is(ANIMAL_NAME));
    }

    @Test
    public void testCreate() {
        when(mockAnimalRepoService.create(animalCaptor.capture())).thenReturn(
                animalRepo);

        Animal animalCreate = new Animal();
        animalCreate.setId(ANIMAL_ID);
        animalCreate.setName(ANIMAL_NAME);

        Animal animal = testService.create(animalCreate);
        assertThat("The animal id", animal.getId(), is(ANIMAL_ID));
        assertThat("The name of the animal", animal.getName(), is(ANIMAL_NAME));
        assertThat("The animal id", animalCaptor.getValue().getId(),
                is(ANIMAL_ID));
        assertThat("The name of the animal", animalCaptor.getValue().getName(),
                is(ANIMAL_NAME));
    }

    @Test
    public void testUpdate() {
        when(mockAnimalRepoService.update(animalCaptor.capture())).thenReturn(
                animalRepo);

        Animal animalCreate = new Animal();
        animalCreate.setId(ANIMAL_ID);
        animalCreate.setName(ANIMAL_NAME);

        Animal animal = testService.update(animalCreate);
        assertThat("The animal id", animal.getId(), is(ANIMAL_ID));
        assertThat("The name of the animal", animal.getName(), is(ANIMAL_NAME));
        assertThat("The animal id", animalCaptor.getValue().getId(),
                is(ANIMAL_ID));
        assertThat("The name of the animal", animalCaptor.getValue().getName(),
                is(ANIMAL_NAME));
    }

    @Test
    public void testDelete() {
        doNothing().when(mockAnimalRepoService).delete(animalCaptor.capture());

        Animal animalCreate = new Animal();
        animalCreate.setId(ANIMAL_ID);
        animalCreate.setName(ANIMAL_NAME);

        testService.delete(animalCreate);
        assertThat("The animal id", animalCaptor.getValue().getId(),
                is(ANIMAL_ID));
        assertThat("The name of the animal", animalCaptor.getValue().getName(),
                is(ANIMAL_NAME));
    }
}
