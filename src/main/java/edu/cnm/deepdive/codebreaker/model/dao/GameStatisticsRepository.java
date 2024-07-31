package edu.cnm.deepdive.codebreaker.model.dao;

import edu.cnm.deepdive.codebreaker.model.entity.GameStatistics;
import edu.cnm.deepdive.codebreaker.model.pojo.GameStatisticsKey;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface GameStatisticsRepository extends ReadOnlyRepository<GameStatistics, GameStatisticsKey> {

  String NO_THRESHOLD_QUERY = """
      SELECT 
        gs 
      FROM 
        GameStatistics AS gs 
      WHERE 
        gs.poolSize = :poolSize 
        AND gs.codeLength = :codeLength 
      ORDER BY 
        gs.avgGuessCount ASC, 
        gs.avgDuration ASC
      """;
  String THRESHOLD_QUERY = """
      SELECT
        gs
      FROM
        GameStatistics AS gs
      WHERE
        gs.poolSize = :poolSize
        AND gs.codeLength = :codeLength
        AND gs.gamesPlayed >= :gamesPlayedThreshold
      ORDER BY
        gs.avgGuessCount ASC,
        gs.avgDuration ASC
      """;

  @Query(NO_THRESHOLD_QUERY)
  List<GameStatistics> selectWithoutThreshold(int poolSize, int codeLength);

  @Query(THRESHOLD_QUERY)
  List<GameStatistics> selectWithThreshold(int poolSize, int codeLength, int gamesPlayedThreshold);

}

