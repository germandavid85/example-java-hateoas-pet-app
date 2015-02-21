package com.kadmandu.petme.repository.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * test class for {@link AnimalRepository}
 * 
 * @author German Potes
 */
public class AnimalRepositoryTest {

    @Test
    public void testGetSetValues() {
        AnimalRepository repository = new AnimalRepository();

        final String id = "id123";
        final String name = "Dog";
        repository.setId(id);
        repository.setName(name);

        assertThat("wrong assigned id", repository.getId(), is(id));
        assertThat("wrong assigned name", repository.getName(), is(name));
    }
}