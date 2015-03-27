package com.kadmandu.petme.web.base;

import java.util.List;

public interface IBaseWebService<T> {
    /**
     * Retrieves all the available DTO entities
     * 
     * @return a list with all the DTO entities
     */
    List<T> getAll();

    /**
     * Retrieves one DTO entity by its id
     * 
     * @param entityId the id of the DTO entity to search for
     * 
     * @return the matched domain entity
     */
    T getOne(String entityId);

    /**
     * Creates a new DTO entity in the data source.
     * 
     * @param entity the DTO entity to be saved
     * 
     * @return the created DTO entity
     */
    T create(T entity);

    /**
     * Updates an existing DTO entity in the data source
     * 
     * @param entity the DTO entity to update
     * 
     * @return the updated DTO entity
     */
    T update(T entity);

    /**
     * Deletes a DTO entity from the data source
     * 
     * @param entity the DTO entity to be deleted
     */
    void delete(T entity);

}
