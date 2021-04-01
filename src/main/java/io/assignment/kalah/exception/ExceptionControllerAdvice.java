package io.assignment.kalah.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller advice class to manage exceptions at single point
 * 
 * @author Richa
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	
	@ExceptionHandler(GameNotFoundException.class)
	ResponseEntity<Object> gameNotFoundHandler(final GameNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorType(ex.getMessage());
		errorResponse.setTimestamp(LocalDateTime.now().toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalMoveException.class)
	ResponseEntity<Object> illegalMoveHandler(final IllegalMoveException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorType(ex.getMessage());
		errorResponse.setTimestamp(LocalDateTime.now().toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
