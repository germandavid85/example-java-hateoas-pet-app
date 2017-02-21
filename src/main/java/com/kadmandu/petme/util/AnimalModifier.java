package com.kadmandu.petme.util;

import com.kadmandu.petme.repository.entity.Animal;
import com.kadmandu.petme.repository.entity.Breed;
import org.springframework.hateoas.Resource;

import java.util.List;

public class AnimalModifier {
    private static final String GOLDEN_RETRIEVER = "Golden Retriever";

    public static void modify(final List<Animal> animals) {
        for (Animal animal : animals) {
            if (animal.getAnimalId() != null) {
                if(animal.getName() != null) {
                    if (animal.getBreeds() != null) {
                        List<Breed> breeds = animal.getBreeds();
                        if (breeds.size() > 0) {
                            for (Breed breed : breeds) {
                                if (breed != null) {
                                    if (breed.getName() != null) {
                                        if (breed.getName().equals(GOLDEN_RETRIEVER)) {
                                            String currentName = animal.getName();
                                            animal.setName(currentName + GOLDEN_RETRIEVER);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
