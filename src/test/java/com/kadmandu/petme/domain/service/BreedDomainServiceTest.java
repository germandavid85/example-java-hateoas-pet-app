package com.kadmandu.petme.domain.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.domain.translator.AnimalDomainPersistenceTranslator;
import com.kadmandu.petme.domain.translator.BreedDomainPersistenceTranslator;
import com.kadmandu.petme.repository.entity.AnimalRepository;
import com.kadmandu.petme.repository.entity.BreedRepository;
import com.kadmandu.petme.repository.service.IBreedRepositoryService;

@RunWith(MockitoJUnitRunner.class)
public class BreedDomainServiceTest {

    private final static String BREED_ID = "br123";
    private final static String BREED_NAME = "Golden Retriever";

    @Mock
    private IBreedRepositoryService mockBreedRepoService;
    @Captor
    private ArgumentCaptor<BreedRepository> breedCaptor;
    @Captor
    private ArgumentCaptor<AnimalRepository> animalCaptor;

    private BreedDomainPersistenceTranslator breedDomPerTranslator;
    private AnimalDomainPersistenceTranslator animalDomPerTranslator;
    private BreedRepository breedRepo;
    private Breed breedCreate;

    private BreedDomainService testService;

    @Before
    public void setUp() {
        animalDomPerTranslator = new AnimalDomainPersistenceTranslator();
        breedDomPerTranslator = new BreedDomainPersistenceTranslator(
                animalDomPerTranslator);
        testService = new BreedDomainService(mockBreedRepoService,
                breedDomPerTranslator, animalDomPerTranslator);
        breedRepo = new BreedRepository();
        breedRepo.setId(BREED_ID);
        breedRepo.setName(BREED_NAME);
        breedRepo.setAnimal(new AnimalRepository());

        breedCreate = new Breed();
        breedCreate.setId(BREED_ID);
        breedCreate.setName(BREED_NAME);
        breedCreate.setAnimal(new Animal());
    }

    @Test
    public void testGetAll() {
        when(mockBreedRepoService.getAll()).thenReturn(
                Collections.singletonList(breedRepo));
        List<Breed> breeds = testService.getAll();

        assertThat("The size of breed", breeds.size(), is(1));
        assertThat("The breed id", breeds.get(0).getId(), is(BREED_ID));
        assertThat("The name of the breed", breeds.get(0).getName(),
                is(BREED_NAME));
    }

    @Test
    public void testGetOne() {
        when(mockBreedRepoService.getOne(BREED_ID)).thenReturn(breedRepo);

        Breed breed = testService.getOne(BREED_ID);
        assertThat("The breed id", breed.getId(), is(BREED_ID));
        assertThat("The name of the breed", breed.getName(), is(BREED_NAME));
    }

    @Test
    public void testCreate() {
        when(mockBreedRepoService.create(breedCaptor.capture())).thenReturn(
                breedRepo);

        Breed breed = testService.create(breedCreate);
        assertThat("The breed id", breed.getId(), is(BREED_ID));
        assertThat("The name of the breed", breed.getName(), is(BREED_NAME));
        assertThat("The breed id", breedCaptor.getValue().getId(), is(BREED_ID));
        assertThat("The name of the breed", breedCaptor.getValue().getName(),
                is(BREED_NAME));
    }

    @Test
    public void testUpdate() {
        when(mockBreedRepoService.update(breedCaptor.capture())).thenReturn(
                breedRepo);

        Breed breed = testService.update(breedCreate);
        assertThat("The breed id", breed.getId(), is(BREED_ID));
        assertThat("The name of the breed", breed.getName(), is(BREED_NAME));
        assertThat("The breed id", breedCaptor.getValue().getId(), is(BREED_ID));
        assertThat("The name of the breed", breedCaptor.getValue().getName(),
                is(BREED_NAME));
    }

    @Test
    public void testDelete() {
        doNothing().when(mockBreedRepoService).delete(breedCaptor.capture());

        testService.delete(breedCreate);
        assertThat("The breed id", breedCaptor.getValue().getId(), is(BREED_ID));
        assertThat("The name of the breed", breedCaptor.getValue().getName(),
                is(BREED_NAME));
    }

    @Test
    public void testGetByAnimal() {
        when(mockBreedRepoService.getByAnimal(animalCaptor.capture()))
                .thenReturn(Collections.singletonList(breedRepo));

        Animal animal = new Animal();
        animal.setId("an123");
        animal.setName("Dog");
        List<Breed> breeds = testService.getByAnimal(animal);

        assertThat("The size of breed", breeds.size(), is(1));
        assertThat("The breed id", breeds.get(0).getId(), is(BREED_ID));
        assertThat("The name of the breed", breeds.get(0).getName(),
                is(BREED_NAME));
        assertThat("The animal id", animalCaptor.getValue().getId(),
                is("an123"));
    }
}
