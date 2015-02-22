package com.kadmandu.petme.domain.translator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.repository.entity.AnimalRepository;

/**
 * test class for {@link AnimalDomainPersistenceTranslator}
 * 
 * @author German Potes
 */
public class AnimalDomainPersistenceTranslatorTest {

    private final static String ANIMAL_ID = "an123";
    private final static String ANIMAL_NAME = "Dog";

    private AnimalDomainPersistenceTranslator testAnimalTranslator;
    private Animal animal;
    private AnimalRepository animalRepo;

    @Before
    public void setUp() {
        testAnimalTranslator = new AnimalDomainPersistenceTranslator();
        animal = new Animal();
        animal.setId(ANIMAL_ID);
        animal.setName(ANIMAL_NAME);

        animalRepo = new AnimalRepository();
        animalRepo.setId(ANIMAL_ID);
        animalRepo.setName(ANIMAL_NAME);
    }

    @Test
    public void testTranslateFrom() {
        final Animal animal = testAnimalTranslator.translateFrom(animalRepo);

        assertThat("The id of the animal", animal.getId(), is(ANIMAL_ID));
        assertThat("The name of the animal", animal.getName(), is(ANIMAL_NAME));
    }

    @Test
    public void testTranslateTo() {
        final AnimalRepository animalRepo = testAnimalTranslator
                .translateTo(animal);

        assertThat("The id of the animal", animalRepo.getId(), is(ANIMAL_ID));
        assertThat("The name of the animal", animalRepo.getName(),
                is(ANIMAL_NAME));
    }

}
