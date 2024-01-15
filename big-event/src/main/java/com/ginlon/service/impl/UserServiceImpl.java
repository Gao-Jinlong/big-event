package com.ginlon.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginlon.mapper.UserMapper;
import com.ginlon.pojo.User;
import com.ginlon.service.UserService;
import com.ginlon.utils.Md5Util;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User findByUsername(String username) {
    return userMapper.findByUsername(username);
  }

  @Override
  public void register(String username, String password) {
    // 加密
    String md5String = Md5Util.encode(password);

    // 添加
    userMapper.add(username, md5String);
  }

  @Override
  public void update(User user) {
    user.setUpdateTime(LocalDateTime.now());
    userMapper.update(user);
  }

}
