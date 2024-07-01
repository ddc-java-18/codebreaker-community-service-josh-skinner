package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.dao.GameRepository;
import edu.cnm.deepdive.codebreaker.model.dao.GuessRepository;
import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.model.entity.Guess;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GameService implements AbstractGameService {

  private final GameRepository gameRepository;
  private final GuessRepository guessRepository;

  public GameService(GameRepository gameRepository, GuessRepository guessRepository) {
    this.gameRepository = gameRepository;
    this.guessRepository = guessRepository;
  }

  @Override
  public Game startGame(Game game, User user) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Game getGame(UUID gameKey, User user) {
    return gameRepository
        .getByExternalKeyAndPlayer(gameKey, user)
        .orElseThrow();
  }

  @Override
  public Guess submitGuess(UUID gameKey, Guess guess, User user) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Guess getGuess(UUID gameKey, UUID guessKey, User user) {
    return guessRepository
        .getByExternalKeyAndGameExternalKeyAndGamePlayer(guessKey, gameKey, user)
        .orElseThrow();
  }
}
