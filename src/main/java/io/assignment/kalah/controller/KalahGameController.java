package io.assignment.kalah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.assignment.kalah.dto.KalahGameDTO;
import io.assignment.kalah.service.KalahGameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for Mancala Kalah Game
 * 
 * @author Richa
 *
 */
@RestController
@Slf4j
@RequestMapping("/games")
@Api(value = "Kalah game API. Endpoints for Creating and Playing the Kalah game")
public class KalahGameController {

	/**
	 * {@code KalahGameService} instance
	 */
	private final KalahGameService service;

	@Autowired
	public KalahGameController(final KalahGameService service) {
		this.service = service;
	}

	/**
	 * This method creates Kalah game.
	 * 
	 * @return {@link KalahGameDTO} wrapped in {@link ResponseEntity}
	 */
	@ApiOperation(value = "Endpoint for creating new Kalah game instance. It returns a KalahGame object with unique GameId used for playing the game")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KalahGameDTO> createGame() {

		final KalahGameDTO kalahGameDTO = service.createGame();
		log.debug("Game instance created. Id=" + kalahGameDTO.getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(kalahGameDTO);
	}
	
	

	/**
	 * This method is used to make a move.
	 * 
	 * @param gameId unique identifier of a game
	 * @param pitId  id of the pit selected to make a move. Pits are numbered from 1
	 *               to 14 where 7 and 14 are the kalah (or house) of each player
	 * @return {@link KalahGameDTO} wrapped in {@link ResponseEntity}
	 */
	@ApiOperation(value = "Endpoint for playing the game")
	@PutMapping(path = "{gameId}/pits/{pitId}", produces = "Application/JSON")
	public ResponseEntity<KalahGameDTO> makeMove(@PathVariable(value = "gameId") String gameId,
			@PathVariable(value = "pitId") Integer pitId) {
		final KalahGameDTO kalahGameDTO = service.makeMove(gameId, pitId);

		return ResponseEntity.status(HttpStatus.OK).body(kalahGameDTO);
	}

}
