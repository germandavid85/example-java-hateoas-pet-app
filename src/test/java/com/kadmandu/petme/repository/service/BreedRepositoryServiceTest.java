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

import com.kadmandu.petme.repository.entity.Breed;

/**
 * test class for {@link BreedRepositoryService}
 * 
 * @author German Potes
 */
@RunWith(MockitoJUnitRunner.class)
public class BreedRepositoryServiceTest
{

    private static final String BREED_ID = "id123";
    private static final String BREED_NAME = "Golden Retriever";

    @Mock
    private BreedRepository mockBreedRepository;
    @Mock
    private AnimalRepository mockAnimalRepository;
    private BreedRepositoryService testService;
    private Breed breed1;

    @Before
    public void setUp()
    {
        breed1 = new Breed();
        breed1.setBreedId(BREED_ID);
        breed1.setName(BREED_NAME);

        testService = new BreedRepositoryService(mockBreedRepository, mockAnimalRepository);
    }

    @Test
    public void testGetAll()
    {
        when(mockBreedRepository.findAll()).thenReturn(Collections.singletonList(breed1));
        List<Breed> breeds = testService.getAll();
        assertThat("The size of breeds", breeds.size(), is(1));
        assertThat("The id of the breed", breeds.get(0).getBreedId(), is(BREED_ID));
    }

    @Test
    public void testGetOne()
    {
        when(mockBreedRepository.findOne(BREED_ID)).thenReturn(breed1);
        Breed breed = testService.getOne(BREED_ID);
        assertThat("The id of the breed", breed.getBreedId(), is(BREED_ID));
        assertThat("The id of the breed", breed.getName(), is(BREED_NAME));
    }

    @Test
    public void testCreate()
    {
        Breed returnedBreed = new Breed();
        when(mockBreedRepository.save(breed1)).thenReturn(returnedBreed);

        assertThat("The returned breed", testService.create(breed1),
            is(sameInstance(returnedBreed)));
        verify(mockBreedRepository).save(breed1);
    }

    @Test
    public void testUpdate()
    {
        Breed returnedBreed = new Breed();
        when(mockBreedRepository.save(breed1)).thenReturn(returnedBreed);

        assertThat("The returned breed", testService.update(breed1),
            is(sameInstance(returnedBreed)));
        verify(mockBreedRepository).save(breed1);
    }

    @Test
    public void testDelete()
    {
        doNothing().when(mockBreedRepository).delete(breed1);

        testService.delete(breed1);
        verify(mockBreedRepository).delete(breed1);
    }

}
