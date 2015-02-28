package com.kadmandu.petme.domain.translator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kadmandu.petme.domain.entity.Breed;
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

    private BreedDomainDTOTranslator testBreedTranslator;
    private Breed breed;
    private BreedDTO breedDto;

    @Before
    public void setUp() {
        testBreedTranslator = new BreedDomainDTOTranslator();
        breed = new Breed();
        breed.setId(BREED_ID);
        breed.setName(BREED_NAME);

        breedDto = new BreedDTO();
        breedDto.setId(BREED_ID);
        breedDto.setName(BREED_NAME);
    }

    @Test
    public void testTranslateFrom() {
        final Breed breed = testBreedTranslator.translateFrom(breedDto);

        assertThat("The id of the breed", breed.getId(), is(BREED_ID));
        assertThat("The name of the breed", breed.getName(), is(BREED_NAME));
    }

    @Test
    public void testTranslateTo() {
        final BreedDTO breedDto = testBreedTranslator.translateTo(breed);

        assertThat("The id of the breed", breedDto.getId(), is(BREED_ID));
        assertThat("The name of the breed", breedDto.getName(), is(BREED_NAME));
    }
}
