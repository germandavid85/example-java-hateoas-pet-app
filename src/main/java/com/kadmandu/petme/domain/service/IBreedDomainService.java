package com.kadmandu.petme.domain.service;

import java.util.List;

import com.kadmandu.petme.domain.base.IBaseDomainService;
import com.kadmandu.petme.domain.entity.Animal;
import com.kadmandu.petme.domain.entity.Breed;

/**
 * Interface containing the operations to perform against the domain layer for breeds
 * 
 * @author German Potes
 */
public interface IBreedDomainService extends IBaseDomainService<Breed> {

    List<Breed> getByAnimal(final Animal animal);
}
