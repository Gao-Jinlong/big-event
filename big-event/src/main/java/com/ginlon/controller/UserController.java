package com.ginlon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ginlon.pojo.Result;
import com.ginlon.pojo.User;
import com.ginlon.service.UserService;

import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public Result register(@Pattern(regexp = "^\\S{5,16}") String username,
      @Pattern(regexp = "^\\S{5,16}") String password) {
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
