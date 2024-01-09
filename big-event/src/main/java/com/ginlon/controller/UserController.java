package com.ginlon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ginlon.pojo.Result;
import com.ginlon.pojo.User;
import com.ginlon.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public Result register(String username, String password) {
    // 查询用户
    User user = userService.findByUsername(username);

    if (user == null) {
      // 没有占用
      // 注册
      userService.register(username, password);
      return Result.success();
    } else {
      // 已经占用
      return Result.error("用户名已经被占用");
    }
    // 注册
  }

}
