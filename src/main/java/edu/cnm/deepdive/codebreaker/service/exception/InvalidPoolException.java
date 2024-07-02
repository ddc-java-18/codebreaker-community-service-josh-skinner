package edu.cnm.deepdive.codebreaker.service.exception;

public class InvalidPoolException extends IllegalArgumentException {

  public InvalidPoolException() {
  }

  public InvalidPoolException(String message) {
    super(message);
  }

  public InvalidPoolException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidPoolException(Throwable cause) {
    super(cause);
  }
}
