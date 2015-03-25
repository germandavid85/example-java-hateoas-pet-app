package com.kadmandu.petme.repository.service;

import com.kadmandu.petme.repository.base.IBaseRepositoryService;
import com.kadmandu.petme.repository.entity.Animal;
import com.kadmandu.petme.repository.entity.Breed;

/**
 * Interface containing the operations to perform against the mongo data source for breeds
 * 
 * @author German Potes
 */
public interface IBreedRepositoryService extends IBaseRepositoryService<Breed> {
    Animal getOne(String animalId, String breedId);
}
