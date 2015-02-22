package com.kadmandu.petme.domain.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BreedTest {

    @Test
    public void testGetSetValues() {
        final String breedId = "ani123";
        final String breedName = "Golden Retriever";
        Animal animal = new Animal();
        Breed breed = new Breed();
        breed.setId(breedId);
        breed.setName(breedName);
        breed.setAnimal(animal);

        assertThat("The domain breed imal id", breed.getId(), is(breedId));
        assertThat("The domain breed name", breed.getName(), is(breedName));
        assertThat("The domain breed animal", breed.getAnimal(),
                is(sameInstance(animal)));
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullId() {
        Breed breed = new Breed();
        breed.setId(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName() {
        Breed breed = new Breed();
        breed.setName(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullAnimal() {
        Breed breed = new Breed();
        breed.setAnimal(null);
    }
}
