package com.kadmandu.petme.domain.translator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.domain.entity.Breed;
import com.kadmandu.petme.web.entity.AnimalDTO;
import com.kadmandu.petme.web.entity.BreedDTO;

/**
 * test class for {@link BreedDomainDTOTranslator}
 * 
 * @author German Potes
 *
 */
public class BreedDomainDTOTranslatorTest {

    private final static String BREED_ID = "br123";
    private final static String BREED_NAME = "Golden Retriever";
    private final static String ANIMAL_ID = "an123";
    private final static String ANIMAL_NAME = "Dog";

    private BreedDomainDTOTranslator testBreedTranslator;
    private AnimalDomainDTOTranslator testAnimalTranslator;
    private Breed breed;
    private BreedDTO breedDto;
    private Animal animal;
    private AnimalDTO animalDto;

    @Before
    public void setUp() {
        testAnimalTranslator = new AnimalDomainDTOTranslator();
        testBreedTranslator = new BreedDomainDTOTranslator(testAnimalTranslator);
        breed = new Breed();
        breed.setId(BREED_ID);
        breed.setName(BREED_NAME);
        animal = new Animal();
        animal.setName(ANIMAL_NAME);
        animal.setId(ANIMAL_ID);
        breed.setAnimal(animal);

        breedDto = new BreedDTO();
        breedDto.setId(BREED_ID);
        breedDto.setName(BREED_NAME);
        animalDto = new AnimalDTO();
        animalDto.setName(ANIMAL_NAME);
        animalDto.setId(ANIMAL_ID);
        breedDto.setAnimalDto(animalDto);
    }

    @Test
    public void testTranslateFrom() {
        final Breed breed = testBreedTranslator.translateFrom(breedDto);

        assertThat("The id of the breed", breed.getId(), is(BREED_ID));
        assertThat("The name of the breed", breed.getName(), is(BREED_NAME));
        assertThat("The name of the animal", breed.getAnimal().getId(),
                is(ANIMAL_ID));
        assertThat("The name of the animal", breed.getAnimal().getName(),
                is(ANIMAL_NAME));
    }

    @Test
    public void testTranslateTo() {
        final BreedDTO breedDto = testBreedTranslator.translateTo(breed);

        assertThat("The id of the breed", breedDto.getId(), is(BREED_ID));
        assertThat("The name of the breed", breedDto.getName(), is(BREED_NAME));
        assertThat("The name of the animal", breedDto.getAnimalDto().getId(),
                is(ANIMAL_ID));
        assertThat("The name of the animal", breedDto.getAnimalDto().getName(),
                is(ANIMAL_NAME));
    }

}
