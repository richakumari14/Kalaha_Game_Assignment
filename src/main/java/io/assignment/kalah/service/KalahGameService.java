package io.assignment.kalah.service;

import io.assignment.kalah.dto.KalahGameDTO;

/**
 * KalahGame service interface. It contains method to create game and make move.
 * 
 * @author Richa
 *
 */
public interface KalahGameService {

	/**
	 * Method to create new instance of Kalah Game
	 * 
	 * @return {@link KalahGameDTO} with its attributes.
	 */
	KalahGameDTO createGame();

	/**
	 * Method to make a move
	 * 
	 * @param gameId unique identifier of a game
	 * @param pitId  id of the pit selected to make a move. Pits are numbered from 1
	 *               to 14 where 7 and 14 are the kalah (or house) of each player
	 * 
	 * @return {@link KalahGameDTO} with its attributes.
	 */
	KalahGameDTO makeMove(String gameId, Integer pitId);

}
