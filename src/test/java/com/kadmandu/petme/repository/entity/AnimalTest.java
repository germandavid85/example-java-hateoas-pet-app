package com.kadmandu.petme.repository.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.Collections;

import org.junit.Test;

/**
 * test class for {@link Animal}
 * 
 * @author German Potes
 */
public class AnimalTest
{

    @Test
    public void testGetSetValues()
    {
        Animal repository = new Animal();

        final String id = "id123";
        final String name = "Dog";
        repository.setAnimalId(id);
        repository.setName(name);
        Breed breed = new Breed();
        repository.setBreeds(Collections.singletonList(breed));

        assertThat("wrong assigned id", repository.getAnimalId(), is(id));
        assertThat("wrong assigned name", repository.getName(), is(name));
        assertThat("wrong assigned breed", repository.getBreeds().get(0), is(sameInstance(breed)));
    }
}