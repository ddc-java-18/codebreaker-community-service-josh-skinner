package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.dao.GameStatisticsRepository;
import edu.cnm.deepdive.codebreaker.model.entity.GameStatistics;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService implements AbstractStatisticsService {

  private final GameStatisticsRepository statisticsRepository;

  @Autowired
  public StatisticsService(GameStatisticsRepository statisticsRepository) {
    this.statisticsRepository = statisticsRepository;
  }

  @Override
  public List<GameStatistics> getWithoutThreshold(int poolSize, int codeLength) {
    return statisticsRepository.selectWithoutThreshold(poolSize, codeLength);
  }

  @Override
  public List<GameStatistics> getWithThreshold(
      int poolSize, int codeLength, int gamesPlayedThreshold) {
    return statisticsRepository.selectWithThreshold(poolSize, codeLength, gamesPlayedThreshold);
  }

}