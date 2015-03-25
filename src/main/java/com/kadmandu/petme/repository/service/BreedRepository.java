package com.kadmandu.petme.repository.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kadmandu.petme.repository.entity.Breed;

/**
 * Persistence layer for breed to communicate with the data access.
 * 
 * @author German Potes
 */
public interface BreedRepository extends MongoRepository<Breed, String> {
}
