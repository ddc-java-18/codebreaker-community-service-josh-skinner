package edu.cnm.deepdive.codebreaker.model.dao;

import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {


  List<Game> findAllByPlayerOrderByCreatedDesc(User player);

  List<Game> findAllByPoolSizeAndCodeLength (int poolSize, int codeLength);

  Optional<Game> getByExternalKey(UUID externalKey);

}
