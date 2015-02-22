package com.kadmandu.petme.domain.translator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.repository.entity.AnimalRepository;
import com.kadmandu.petme.repository.entity.BreedRepository;

/**
 * test class for {@link BreedDomainPersistenceTranslator}
 * 
 * @author German Potes
 *
 */
public class BreedDomainPersistenceTranslatorTest {

    private final static String BREED_ID = "br123";
    private final static String BREED_NAME = "Golden Retriever";
    private final static String ANIMAL_ID = "an123";
    private final static String ANIMAL_NAME = "Dog";

    private BreedDomainPersistenceTranslator testBreedTranslator;
    private AnimalDomainPersistenceTranslator testAnimalTranslator;
    private Breed breed;
    private BreedRepository breedRepo;
    private Animal animal;
    private AnimalRepository animalRepo;

    @Before
    public void setUp() {
        testAnimalTranslator = new AnimalDomainPersistenceTranslator();
        testBreedTranslator = new BreedDomainPersistenceTranslator(
                testAnimalTranslator);
        breed = new Breed();
        breed.setId(BREED_ID);
        breed.setName(BREED_NAME);
        animal = new Animal();
        animal.setName(ANIMAL_NAME);
        animal.setId(ANIMAL_ID);
        breed.setAnimal(animal);

        breedRepo = new BreedRepository();
        breedRepo.setId(BREED_ID);
        breedRepo.setName(BREED_NAME);
        animalRepo = new AnimalRepository();
        animalRepo.setName(ANIMAL_NAME);
        animalRepo.setId(ANIMAL_ID);
        breedRepo.setAnimal(animalRepo);
    }

    @Test
    public void testTranslateFrom() {
        final Breed breed = testBreedTranslator.translateFrom(breedRepo);

        assertThat("The id of the breed", breed.getId(), is(BREED_ID));
        assertThat("The name of the breed", breed.getName(), is(BREED_NAME));
        assertThat("The name of the animal", breed.getAnimal().getId(),
                is(ANIMAL_ID));
        assertThat("The name of the animal", breed.getAnimal().getName(),
                is(ANIMAL_NAME));
    }

    @Test
    public void testTranslateTo() {
        final BreedRepository breedRepo = testBreedTranslator
                .translateTo(breed);

        assertThat("The id of the breed", breedRepo.getId(), is(BREED_ID));
        assertThat("The name of the breed", breedRepo.getName(), is(BREED_NAME));
        assertThat("The name of the animal", breedRepo.getAnimal().getId(),
                is(ANIMAL_ID));
        assertThat("The name of the animal", breedRepo.getAnimal().getName(),
                is(ANIMAL_NAME));
    }

}
