package com.ginlon.service;

import com.ginlon.pojo.User;

public interface UserService {
  User findByUsername(String username);

  void register(String username, String password);

  void update(User user);

  void updateAvatar(String avatarUrl);

  void updatePassword(String password);
}
