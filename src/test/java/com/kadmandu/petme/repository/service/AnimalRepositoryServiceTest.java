package com.kadmandu.petme.repository.service;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kadmandu.petme.repository.entity.Animal;

/**
 * test class for {@link AnimalRepositoryService}
 * 
 * @author German Potes
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AnimalRepositoryServiceTest
{

    private static final String ANIMAL_ID = "id123";
    private static final String ANIMAL_NAME = "Dog";

    @Mock
    private AnimalRepository mockAnimalPeristence;
    private AnimalRepositoryService testService;
    private Animal animal1;

    @Before
    public void setUp()
    {
        animal1 = new Animal();
        animal1.setAnimalId(ANIMAL_ID);
        animal1.setName(ANIMAL_NAME);

        testService = new AnimalRepositoryService(mockAnimalPeristence);
    }

    @Test
    public void testGetAll()
    {
        when(mockAnimalPeristence.findAll()).thenReturn(Collections.singletonList(animal1));
        List<Animal> animals = testService.getAll();
        assertThat("The size of animals", animals.size(), is(1));
        assertThat("The id of the animal", animals.get(0).getAnimalId(), is(ANIMAL_ID));
    }

    @Test
    public void testGetOne()
    {
        when(mockAnimalPeristence.findOne(ANIMAL_ID)).thenReturn(animal1);
        Animal animal = testService.getOne(ANIMAL_ID);
        assertThat("The id of the animal", animal.getAnimalId(), is(ANIMAL_ID));
        assertThat("The id of the animal", animal.getName(), is(ANIMAL_NAME));
    }

    @Test
    public void testCreate()
    {
        Animal returnedAnimal = new Animal();
        when(mockAnimalPeristence.save(animal1)).thenReturn(returnedAnimal);

        assertThat("The returned animal", testService.create(animal1),
            is(sameInstance(returnedAnimal)));
        verify(mockAnimalPeristence).save(animal1);
    }

    @Test
    public void testUpdate()
    {
        Animal returnedAnimal = new Animal();
        when(mockAnimalPeristence.save(animal1)).thenReturn(returnedAnimal);

        assertThat("The returned animal", testService.update(animal1),
            is(sameInstance(returnedAnimal)));
        verify(mockAnimalPeristence).save(animal1);
    }

    @Test
    public void testDelete()
    {
        doNothing().when(mockAnimalPeristence).delete(animal1);

        testService.delete(animal1);
        verify(mockAnimalPeristence).delete(animal1);
    }

}
