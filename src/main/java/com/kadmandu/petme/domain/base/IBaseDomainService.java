package com.kadmandu.petme.domain.base;

import java.util.List;

/**
 * Defines the basic operations available to be performed at the domain level.
 * 
 * @author German Potes
 *
 * @param <T> the persistence entity to deal with
 */
public interface IBaseDomainService<T> {
    /**
     * Retrieves all the available domain entities
     * 
     * @return a list with all the domain entities
     */
    List<T> getAll();

    /**
     * Retrieves one domain entity by its id
     * 
     * @param id the id of the domain entity to search for
     * 
     * @return the matched domain entity
     */
    T getOne(String id);

    /**
     * Creates a new domain entity in the data source.
     * 
     * @param entity the domain entity to be saved
     * 
     * @return the created domain entity
     */
    T create(T entity);

    /**
     * Updates an existing domain entity in the data source
     * 
     * @param entity the domain entity to update
     * 
     * @return the updated domain entity
     */
    T update(T entity);

    /**
     * Deletes a domain entity from the data source
     * 
     * @param entity the domain entity to be deleted
     */
    void delete(T entity);

}
