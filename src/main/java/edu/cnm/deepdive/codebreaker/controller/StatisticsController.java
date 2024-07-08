package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.model.entity.GameStatistics;
import edu.cnm.deepdive.codebreaker.service.AbstractStatisticsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rankings")
public class StatisticsController {

  private final AbstractStatisticsService statisticsService;

  @Autowired
  public StatisticsController(AbstractStatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      params = {"pool-size", "code-length", "games-played"})
  List<GameStatistics> get(@RequestParam("pool-size") int poolSize,
      @RequestParam("code-length") int codeLength, @RequestParam("games-played") int gamesPlayed) {
    return statisticsService.getWithThreshold(poolSize, codeLength, gamesPlayed);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"pool-size", "code-length"})
  List<GameStatistics> get(
      @RequestParam("pool-size") int poolSize, @RequestParam("code-length") int codeLength) {
    return statisticsService.getWithoutThreshold(poolSize, codeLength);
  }

}
