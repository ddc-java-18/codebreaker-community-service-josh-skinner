package edu.cnm.deepdive.codebreaker.model.dao;

import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.model.entity.Guess;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface GuessRepository extends CrudRepository<Guess, Long> {

  Optional<Guess> getByExternalKeyAndGame(UUID guessKey, Game game);

  Optional<Guess> getByExternalKeyAndGameExternalKeyAndGamePlayer (UUID guessKey, UUID gameKey, User player);

}
