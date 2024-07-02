package edu.cnm.deepdive.codebreaker.service.exception;

public class InvalidGuessCharacterException extends IllegalArgumentException {

  public InvalidGuessCharacterException() {
  }

  public InvalidGuessCharacterException(String message) {
    super(message);
  }

  public InvalidGuessCharacterException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidGuessCharacterException(Throwable cause) {
    super(cause);
  }
}
