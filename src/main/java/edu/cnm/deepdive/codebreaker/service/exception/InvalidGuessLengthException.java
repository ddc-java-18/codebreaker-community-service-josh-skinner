package edu.cnm.deepdive.codebreaker.service.exception;

public class InvalidGuessLengthException extends IllegalArgumentException {

  public InvalidGuessLengthException() {
  }

  public InvalidGuessLengthException(String message) {
    super(message);
  }

  public InvalidGuessLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidGuessLengthException(Throwable cause) {
    super(cause);
  }
}
