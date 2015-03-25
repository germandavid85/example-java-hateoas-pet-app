package com.kadmandu.petme.repository.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * test class for {@link Breed}
 * 
 * @author German Potes
 */
public class BreedTest
{
    @Test
    public void testGetSetValues()
    {
        Breed breedRepo = new Breed();

        final String id = "id123";
        final String name = "Dog";
        breedRepo.setId(id);
        breedRepo.setName(name);

        assertThat("The id of the breed", breedRepo.getId(), is(id));
        assertThat("The name of the breed", breedRepo.getName(), is(name));
    }
}
