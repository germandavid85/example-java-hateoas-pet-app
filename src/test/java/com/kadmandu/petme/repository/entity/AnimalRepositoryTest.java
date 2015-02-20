package com.kadmandu.petme.repository.entity;

import static org.junit.Assert.assertEquals;

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

        assertEquals("wrong assigned id", id, repository.getId());
        assertEquals("wrong assigned name", name, repository.getName());
    }
}