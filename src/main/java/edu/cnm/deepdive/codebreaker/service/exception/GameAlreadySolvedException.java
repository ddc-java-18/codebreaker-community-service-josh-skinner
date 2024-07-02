package edu.cnm.deepdive.codebreaker.service.exception;

public class GameAlreadySolvedException extends IllegalStateException {

  public GameAlreadySolvedException() {
  }

  public GameAlreadySolvedException(String message) {
    super(message);
  }

  public GameAlreadySolvedException(String message, Throwable cause) {
    super(message, cause);
  }

  public GameAlreadySolvedException(Throwable cause) {
    super(cause);
  }
}
