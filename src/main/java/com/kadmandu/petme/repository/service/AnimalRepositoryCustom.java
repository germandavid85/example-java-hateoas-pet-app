package com.kadmandu.petme.repository.service;

import com.kadmandu.petme.repository.entity.Animal;

public interface AnimalRepositoryCustom {
    Animal getBreedsCustomMethod(String animalId, String breedId);
}
