package com.kadmandu.petme.repository.base;

import java.util.List;

/**
 * Defines the basic operations available to be performed at the persistence level.
 * 
 * @author German Potes
 *
 * @param <T> the persistence entity to deal with
 */
public interface IBaseRepositoryService<T> {
    /**
     * Retrieves all the available entities
     * 
     * @return a list with all the entities
     */
    List<T> getAll();

    /**
     * Retrieves one entity by its id
     * 
     * @param repositoryId the id of the entity to search for
     * 
     * @return the matched entity
     */
    T getOne(String repositoryId);

    /**
     * Creates a new entity in the data source.
     * 
     * @param entity the entity to be saved
     * 
     * @return the created entity
     */
    T create(T entity);

    /**
     * Updates an existing entity in the data source
     * 
     * @param entity the entity to update
     * 
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Deletes an entity from the data source
     * 
     * @param entity the entity to be deleted
     */
    void delete(T entity);
}
