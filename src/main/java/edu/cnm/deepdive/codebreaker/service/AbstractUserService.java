package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.entity.User;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface AbstractUserService {

  User getOrCreate(String oauthKey, String displayName);

  User get(UUID externalKey) throws NoSuchElementException;

  User getCurrentUser();

  User update(User received);


}
