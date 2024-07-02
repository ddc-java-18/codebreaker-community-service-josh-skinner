package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.service.exception.GameAlreadySolvedException;
import edu.cnm.deepdive.codebreaker.service.exception.InvalidGuessCharacterException;
import edu.cnm.deepdive.codebreaker.service.exception.InvalidGuessLengthException;
import edu.cnm.deepdive.codebreaker.service.exception.InvalidPoolException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Specified game or guess not found.") // 404 error corresponds to this error code we will throw
  public void resourceNotFound() {
  }

  @ExceptionHandler(InvalidPoolException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Pool contains invalid Unicode characters.")
  public void invalidPool() {
  }

  @ExceptionHandler(InvalidGuessLengthException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Guess must have same length as secret code.")
  public void invalidGuessLength() {
  }

  @ExceptionHandler(InvalidGuessCharacterException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Guess must only characters from the pool.")
  public void invalidGuessCharacter() {}

  @ExceptionHandler(GameAlreadySolvedException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Guess can not be accepted for a solved game.")
  public void gameAlreadySolved() {}



}
