package com.kadmandu.petme.repository.service;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kadmandu.petme.repository.entity.AnimalRepository;
import com.kadmandu.petme.repository.entity.BreedRepository;

/**
 * Persistence layer for breed to communicate with the data access.
 * 
 * @author German Potes
 */
public interface BreedPersistence extends MongoRepository<BreedRepository, String> {
    List<BreedRepository> findByAnimal(final AnimalRepository animal);
}
