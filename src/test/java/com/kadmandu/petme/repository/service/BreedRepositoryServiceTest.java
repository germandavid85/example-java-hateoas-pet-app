package com.kadmandu.petme.repository.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kadmandu.petme.repository.entity.AnimalRepository;
import com.kadmandu.petme.repository.entity.BreedRepository;

/**
 * test class for {@link BreedRepositoryService}
 * 
 * @author German Potes
 */
@RunWith(MockitoJUnitRunner.class)
public class BreedRepositoryServiceTest {

    private static final String BREED_ID = "id123";
    private static final String BREED_NAME = "Golden Retriever";

    @Mock
    private BreedPersistence mockBreedPersistence;
    private BreedRepositoryService testService;
    private BreedRepository breed1;

    @Before
    public void setUp() {
        breed1 = new BreedRepository();
        breed1.setId(BREED_ID);
        breed1.setName(BREED_NAME);

        testService = new BreedRepositoryService(mockBreedPersistence);
    }

    @Test
    public void testGetAll() {
        when(mockBreedPersistence.findAll()).thenReturn(
                Collections.singletonList(breed1));
        List<BreedRepository> breeds = testService.getAll();
        assertThat("The size of breeds", breeds.size(), is(1));
        assertThat("The id of the breed", breeds.get(0).getId(), is(BREED_ID));
    }

    @Test
    public void testGetOne() {
        when(mockBreedPersistence.findOne(BREED_ID)).thenReturn(breed1);
        BreedRepository breed = testService.getOne(BREED_ID);
        assertThat("The id of the breed", breed.getId(), is(BREED_ID));
        assertThat("The id of the breed", breed.getName(), is(BREED_NAME));
    }

    @Test
    public void testCreate() {
        BreedRepository returnedBreed = new BreedRepository();
        when(mockBreedPersistence.save(breed1)).thenReturn(returnedBreed);

        assertThat("The returned breed", testService.create(breed1),
                is(sameInstance(returnedBreed)));
        verify(mockBreedPersistence).save(breed1);
    }

    @Test
    public void testUpdate() {
        BreedRepository returnedBreed = new BreedRepository();
        when(mockBreedPersistence.save(breed1)).thenReturn(returnedBreed);

        assertThat("The returned breed", testService.update(breed1),
                is(sameInstance(returnedBreed)));
        verify(mockBreedPersistence).save(breed1);
    }

    @Test
    public void testDelete() {
        doNothing().when(mockBreedPersistence).delete(breed1);

        testService.delete(breed1);
        verify(mockBreedPersistence).delete(breed1);
    }

    @Test
    public void testGetByAnimal() {
        AnimalRepository animal = new AnimalRepository();

        List<BreedRepository> breeds = Collections.singletonList(breed1);
        when(mockBreedPersistence.findByAnimal(animal)).thenReturn(breeds);

        testService.getByAnimal(animal);
        verify(mockBreedPersistence).findByAnimal(animal);
    }

}
