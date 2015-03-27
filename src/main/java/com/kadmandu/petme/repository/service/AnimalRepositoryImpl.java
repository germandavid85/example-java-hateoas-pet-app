package com.kadmandu.petme.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.google.common.base.Preconditions;
import com.kadmandu.petme.repository.entity.Animal;

public class AnimalRepositoryImpl implements AnimalRepositoryCustom
{

    private final MongoOperations operations;

    @Autowired
    public AnimalRepositoryImpl(final MongoOperations operations)
    {
        this.operations = Preconditions.checkNotNull(operations, "operations");
    }

    @Override
    public Animal getBreedsCustomMethod(final String animalId, final String breedId)
    {
        Query mongoQuery = new Query(Criteria.where("id").is(animalId).and("breeds.id").is(breedId));
        mongoQuery.fields().include("breeds.$");

        return operations.findOne(mongoQuery, Animal.class);
    }
}
