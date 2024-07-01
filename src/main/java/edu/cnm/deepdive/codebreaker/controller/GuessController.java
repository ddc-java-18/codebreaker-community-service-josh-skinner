package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.model.entity.Guess;
import edu.cnm.deepdive.codebreaker.service.AbstractGameService;
import edu.cnm.deepdive.codebreaker.service.AbstractUserService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games/{gameKey}/guesses")
public class GuessController {

  private final AbstractGameService gameService;
  private final AbstractUserService userService;

  @Autowired
  public GuessController(AbstractGameService gameService, AbstractUserService userService) {
    this.gameService = gameService;
    this.userService = userService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Guess> get(@PathVariable UUID gameKey) {
    return gameService.getGame(gameKey, userService.getCurrentUser())
        .getGuesses();
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Guess post(@PathVariable UUID gameKey, @RequestBody Guess guess) {
    // TODO: 7/1/24 : Return response with 201 Status and Location header.
    return gameService.submitGuess(gameKey, guess, userService.getCurrentUser());
  }

  @GetMapping(path = "/{guessKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Guess get(@PathVariable UUID gameKey, @PathVariable UUID guessKey) {
    return gameService.getGuess(gameKey, guessKey, userService.getCurrentUser());
  }


}
