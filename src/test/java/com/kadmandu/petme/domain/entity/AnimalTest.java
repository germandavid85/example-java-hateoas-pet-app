package com.kadmandu.petme.domain.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * test class for {@link Animal}
 * 
 * @author German Potes
 */
public class AnimalTest {

    @Test
    public void testGetSetValues() {
        final String animalId = "ani123";
        final String animalName = "Dog";
        Animal animal = new Animal();
        animal.setId(animalId);
        animal.setName(animalName);

        assertThat("The domain animal id", animal.getId(), is(animalId));
        assertThat("The domain animal name", animal.getName(), is(animalName));
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullId() {
        Animal animal = new Animal();
        animal.setId(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName() {
        Animal animal = new Animal();
        animal.setName(null);
    }
}
