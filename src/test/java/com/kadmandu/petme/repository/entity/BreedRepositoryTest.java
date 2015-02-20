package com.kadmandu.petme.repository.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * test class for {@link BreedRepository}
 * 
 * @author German Potes
 */
public class BreedRepositoryTest {

    @Test
    public void testGetSetValues() {
        BreedRepository breedRepo = new BreedRepository();
        AnimalRepository animalRepo = new AnimalRepository();

        final String id = "id123";
        final String name = "Dog";
        breedRepo.setId(id);
        breedRepo.setName(name);
        breedRepo.setAnimal(animalRepo);

        assertThat("The id of the breed", breedRepo.getId(), is(id));
        assertThat("The name of the breed", breedRepo.getName(), is(name));
        assertThat("The animal of the breed", breedRepo.getAnimal(), sameInstance(animalRepo));
    }
}
