package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.entity.GameStatistics;
import java.util.List;

public interface AbstractStatisticsService {



  List<GameStatistics> getWithoutThreshold (int poolSize, int codeLength);

  List<GameStatistics> getWithThreshold(int poolSize, int codeLength, int gamesPlayedThreshold);

}
