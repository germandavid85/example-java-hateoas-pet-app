package com.kadmandu.petme.repository.translator;

/**
 * Defines the translation operations between persistence and DTO objects.
 * 
 * @author German Potes
 *
 * @param <K> the domain type
 * @param <V> the other layer type
 */
public interface Translator <K, V>{
    /**
     * Translate from DTO object to persistence object
     * 
     * @param fromEntity the DTO layer entity
     * 
     * @return the translated persistence entity
     */
    K translateFrom(V fromEntity);

    /**
     * Translate to DTO object from persistence object
     * 
     * @param toEntity the persistence entity
     * 
     * @return the translated DTO layer entity
     */
    V translateTo(K toEntity);
}
