package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.model.entity.Guess;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import java.util.UUID;

public interface AbstractGameService {

  Game startGame(Game game, User user);

  Game getGame(UUID gameKey, User user);

  Guess submitGuess(UUID gameKey, Guess guess, User user);

  Guess getGuess(UUID gameKey, UUID guessKey, User user);

}
