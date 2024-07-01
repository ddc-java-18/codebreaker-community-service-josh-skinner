package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.model.entity.Guess;
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

  private final AbstractUserService userService;

  // TODO: 7/1/24 : Declare and initialize (in constructor) any additional service dependencies
  @Autowired
  public GuessController(AbstractUserService userService) {
    this.userService = userService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Guess> get(@PathVariable UUID gameKey) {
    // TODO: 7/1/24 : Invoke game service method to retrieve and return list of guesses from specified game.
    throw new UnsupportedOperationException();
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Guess post(@PathVariable UUID gameKey, @RequestBody Guess guess) {
    // TODO: 7/1/24 : Invoke game service method to validate and add specified guess to specified game.
    throw new UnsupportedOperationException();
  }

  @GetMapping(path = "/{guessKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Guess get(@PathVariable UUID gameKey, @PathVariable UUID guessKey) {
    // TODO: 7/1/24 : Invoke game service method to retrieve and return specified guess in specified game.
    throw new UnsupportedOperationException();
  }


}
