package io.assignment.kalah.repository;

import io.assignment.kalah.model.KalahGame;

/**
 * <em>In-memory data structure is being used here as repository.</em> DAO layer
 * for containing methods to perform operations on {@code KalahGame}. 
 * 
 * @author Richa
 *
 */
public interface KalahGameRepository {

    KalahGame findById(final String id);

    KalahGame save(final KalahGame kalahGame);

}
