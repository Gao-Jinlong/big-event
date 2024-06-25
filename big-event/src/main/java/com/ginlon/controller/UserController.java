package com.ginlon.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ginlon.pojo.Result;
import com.ginlon.pojo.User;
import com.ginlon.service.UserService;
import com.ginlon.utils.JwtUtil;
import com.ginlon.utils.Md5Util;
import com.ginlon.utils.ThreadLocalUtil;

import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

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

  @PostMapping("/login")
  public Result<String> login(@Pattern(regexp = "^\\S{5,16}") String username,
      @Pattern(regexp = "^\\S{5,16}") String password) {

    // 查询用户
    User loginUser = userService.findByUsername(username);

    // 判断用户是否存在
    if (loginUser == null) {
      return Result.error("用户名错误");
    }

    // 判断密码是否正确
    if (Md5Util.encode(password).equals(loginUser.getPassword())) {
      Map<String, Object> claims = new HashMap<>();
      claims.put("username", username);
      claims.put("id", loginUser.getId());
      String token = JwtUtil.genToken(claims);

      // token 存入 redis
      ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
      operations.set(token, token, 1, TimeUnit.HOURS);

      return Result.success(token);
    }

    return Result.error("密码错误");
  }

  @GetMapping("/userInfo")
  public Result<User> userInfo(/* @RequestHeader("Authorization") String token */) {
    // Map<String, Object> claims = JwtUtil.parseToken(token);

    Map<String, Object> claims = ThreadLocalUtil.get();
    String username = (String) claims.get("username");

    User user = userService.findByUsername(username);
    return Result.success(user);
  }

  @PutMapping("/update")
  public Result update(@RequestBody @Validated User user) {
    userService.update(user);
    return Result.success();

  }

  @PatchMapping("/updateAvatar")
  public Result updateAvatar(@RequestParam @URL String avatarUrl) {
    userService.updateAvatar(avatarUrl);
    return Result.success();
  }

  @PatchMapping("/updatePassword")
  public Result updatePassword(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
    // 旧密码
    String oldPassword = params.get("oldPassword");
    // 新密码
    String newPassword = params.get("newPassword");

    if (!StringUtils.hasLength(oldPassword) || !StringUtils.hasLength(newPassword)) {
      return Result.error("密码不能为空");
    }

    // 获取用户信息
    Map<String, Object> claims = ThreadLocalUtil.get();
    String username = (String) claims.get("username");
    User loginUser = userService.findByUsername(username);
    if (!loginUser.getPassword().equals(Md5Util.encode(oldPassword))) {
      return Result.error("旧密码错误");
    }

    userService.updatePassword(newPassword);

    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
    operations.getOperations().delete(token);

    return Result.success();
  }
}
