package com.kadmandu.petme.domain.translator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.web.entity.AnimalDTO;

/**
 * test class for {@link AnimalDomainDTOTranslator}
 * 
 * @author German Potes
 */
public class AnimalDomainDTOTranslatorTest {

    private final static String ANIMAL_ID = "an123";
    private final static String ANIMAL_NAME = "Dog";

    private AnimalDomainDTOTranslator testAnimalTranslator;
    private Animal animal;
    private AnimalDTO animalDto;

    @Before
    public void setUp() {
        testAnimalTranslator = new AnimalDomainDTOTranslator();
        animal = new Animal();
        animal.setId(ANIMAL_ID);
        animal.setName(ANIMAL_NAME);

        animalDto = new AnimalDTO();
        animalDto.setId(ANIMAL_ID);
        animalDto.setName(ANIMAL_NAME);
    }

    @Test
    public void testTranslateFrom() {
        final Animal animal = testAnimalTranslator.translateFrom(animalDto);

        assertThat("The id of the animal", animal.getId(), is(ANIMAL_ID));
        assertThat("The name of the animal", animal.getName(), is(ANIMAL_NAME));
    }

    @Test
    public void testTranslateTo() {
        final AnimalDTO animalDto = testAnimalTranslator.translateTo(animal);

        assertThat("The id of the animal", animalDto.getId(), is(ANIMAL_ID));
        assertThat("The name of the animal", animalDto.getName(),
                is(ANIMAL_NAME));
    }
}
