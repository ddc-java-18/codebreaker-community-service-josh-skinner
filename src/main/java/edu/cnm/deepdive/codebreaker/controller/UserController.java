package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.model.entity.User;
import edu.cnm.deepdive.codebreaker.service.AbstractUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final AbstractUserService userService;

  @Autowired
  public UserController(AbstractUserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get () {
    return userService.getCurrentUser();
  }

  // TODO: 7/1/24 : Add a method to receive and handle and update request. 

}
