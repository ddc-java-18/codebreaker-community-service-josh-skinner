package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.dao.GameRepository;
import edu.cnm.deepdive.codebreaker.model.dao.GuessRepository;
import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.model.entity.Guess;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import edu.cnm.deepdive.codebreaker.service.exception.GameAlreadySolvedException;
import edu.cnm.deepdive.codebreaker.service.exception.InvalidGuessLengthException;
import edu.cnm.deepdive.codebreaker.service.exception.InvalidPoolException;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.random.RandomGenerator;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GameService implements AbstractGameService {

  private final GameRepository gameRepository;
  private final GuessRepository guessRepository;
  private final RandomGenerator rng;

  public GameService(GameRepository gameRepository, GuessRepository guessRepository,
      RandomGenerator rng) {
    this.gameRepository = gameRepository;
    this.guessRepository = guessRepository;
    this.rng = rng;
  }

  @Override
  public Game startGame(Game game, User user) throws InvalidPoolException {
    game.setPool(validatePool(game.getPool()));
    game.setPlayer(user);
    game.setSecretCode(generateSecretCode(game.getPool(), game.getCodeLength()));
    return gameRepository.save(game);
  }

  @Override
  public Game getGame(UUID gameKey, User user) throws NoSuchElementException, InvalidPoolException {
    return gameRepository
        .getByExternalKeyAndPlayer(gameKey, user)
        .orElseThrow();
  }

  @Override
  public Guess submitGuess(UUID gameKey, Guess guess, User user)
      throws NoSuchElementException, InvalidGuessLengthException, InvalidPoolException, GameAlreadySolvedException {
    return gameRepository
        .getByExternalKeyAndPlayer(gameKey, user)
        .map((game) -> {
          validateGuess(game, guess);
          evaluateGuess(game, guess);
          guess.setGame(game);
          return guessRepository.save(guess);
        })
        .orElseThrow();
  }

  @Override
  public Guess getGuess(UUID gameKey, UUID guessKey, User user)
      throws NoSuchElementException, InvalidPoolException {
    return guessRepository
        .getByExternalKeyAndGameExternalKeyAndGamePlayer(guessKey, gameKey, user)
        .orElseThrow();
  }

  private static String validatePool(String pool) throws InvalidPoolException {
    String validated = pool
        .codePoints()
        .peek(GameService::validateCodePoint)
        .distinct()
        .sorted()
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    return pool;
  }

  private static int[] codePoints(String input) {
    return input
        .codePoints()
        .toArray();
  }

  private static void validateCodePoint(int codePoint) throws InvalidPoolException {
    if (Character.isWhitespace(codePoint)
        || Character.isISOControl(codePoint)
        || !Character.isDefined(codePoint)) {
      throw new InvalidPoolException();
    }
  }

  private String generateSecretCode(String pool, int codeLength) {
    int[] poolCodePoints = codePoints(pool);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < codeLength; i++) {
      builder.appendCodePoint(poolCodePoints[rng.nextInt(poolCodePoints.length)]);
    }
    return builder.toString();
  }

  private static void validateGuess(Game game, Guess guess)
      throws InvalidGuessLengthException, InvalidPoolException, GameAlreadySolvedException {
    throw new UnsupportedOperationException();
  }

  private static void evaluateGuess(Game game, Guess guess) {
    throw new UnsupportedOperationException();
  }

}
