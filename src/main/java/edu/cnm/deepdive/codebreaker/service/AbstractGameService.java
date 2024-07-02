package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.model.entity.Guess;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import edu.cnm.deepdive.codebreaker.service.exception.GameAlreadySolvedException;
import edu.cnm.deepdive.codebreaker.service.exception.InvalidGuessCharacterException;
import edu.cnm.deepdive.codebreaker.service.exception.InvalidGuessLengthException;
import edu.cnm.deepdive.codebreaker.service.exception.InvalidPoolException;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface AbstractGameService {

  /**
   *
   * @param game
   * @param user
   * @return
   * @throws InvalidPoolException
   */


  Game startGame(Game game, User user) throws InvalidPoolException;

  Game getGame(UUID gameKey, User user) throws NoSuchElementException;

  Guess submitGuess(UUID gameKey, Guess guess, User user)
      throws NoSuchElementException, InvalidGuessLengthException,
      InvalidGuessCharacterException, GameAlreadySolvedException;

  Guess getGuess(UUID gameKey, UUID guessKey, User user) throws NoSuchElementException;

}
