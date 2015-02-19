package com.kadmandu.petme.domain.translator;

/**
 * Defines the translation operations among the other layers and the domain layer.
 * 
 * @author German Potes
 *
 * @param <K> the domain type
 * @param <V> the other layer type
 */
public interface Translator <K, V>{
    /**
     * Translate from other layer to domain layer
     * 
     * @param fromEntity the other layer entity
     * 
     * @return the translated domain entity
     */
    K translateFrom(V fromEntity);

    /**
     * Translate to other layer from domain layer
     * 
     * @param toEntity the domain entity
     * 
     * @return the translated other layer entity
     */
    V translateTo(K toEntity);
}
