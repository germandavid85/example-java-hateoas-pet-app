package com.kadmandu.petme.repository.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kadmandu.petme.repository.entity.AnimalRepository;

/**
 * Persistence layer for animal to communicate with the data access.
 * 
 * @author German Potes
 */
public interface AnimalPersistence extends MongoRepository<AnimalRepository, String>{

}
