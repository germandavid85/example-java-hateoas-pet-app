package com.kadmandu.petme.repository.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kadmandu.petme.repository.entity.AnimalRepository;

/**
 * test class for {@link AnimalRepositoryService}
 * 
 * @author German Potes
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AnimalRepositoryServiceTest {

    private static final String ANIMAL_ID = "id123";
    private static final String ANIMAL_NAME = "Dog";

    @Mock
    private AnimalPersistence mockAnimalPeristence;
    private AnimalRepositoryService testService;
    private AnimalRepository animal1;

    @Before
    public void setUp() {
        animal1 = new AnimalRepository();
        animal1.setId(ANIMAL_ID);
        animal1.setName(ANIMAL_NAME);

        testService = new AnimalRepositoryService(mockAnimalPeristence);
    }

    @Test
    public void testGetAll() {
        when(mockAnimalPeristence.findAll()).thenReturn(
                Collections.singletonList(animal1));
        List<AnimalRepository> animals = testService.getAll();
        assertThat("The size of animals", animals.size(), is(1));
        assertThat("The id of the animal", animals.get(0).getId(),
                is(ANIMAL_ID));
    }

    @Test
    public void testGetOne() {
        when(mockAnimalPeristence.findOne(ANIMAL_ID)).thenReturn(animal1);
        AnimalRepository animal = testService.getOne(ANIMAL_ID);
        assertThat("The id of the animal", animal.getId(), is(ANIMAL_ID));
        assertThat("The id of the animal", animal.getName(), is(ANIMAL_NAME));
    }

}
