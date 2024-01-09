package com.ginlon.service;

import com.ginlon.pojo.User;

public interface UserService {
  User findByUsername(String username);

  void register(String username, String password);
}
