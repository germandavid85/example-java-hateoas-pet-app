package com.kadmandu.petme.domain.translator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kadmandu.petme.domain.entity.Breed;
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

    private BreedDomainPersistenceTranslator testBreedTranslator;
    private Breed breed;
    private BreedRepository breedRepo;

    @Before
    public void setUp() {
        testBreedTranslator = new BreedDomainPersistenceTranslator();
        breed = new Breed();
        breed.setId(BREED_ID);
        breed.setName(BREED_NAME);

        breedRepo = new BreedRepository();
        breedRepo.setId(BREED_ID);
        breedRepo.setName(BREED_NAME);
    }

    @Test
    public void testTranslateFrom() {
        final Breed breed = testBreedTranslator.translateFrom(breedRepo);

        assertThat("The id of the breed", breed.getId(), is(BREED_ID));
        assertThat("The name of the breed", breed.getName(), is(BREED_NAME));
    }

    @Test
    public void testTranslateTo() {
        final BreedRepository breedRepo = testBreedTranslator
                .translateTo(breed);

        assertThat("The id of the breed", breedRepo.getId(), is(BREED_ID));
        assertThat("The name of the breed", breedRepo.getName(), is(BREED_NAME));
    }
}
