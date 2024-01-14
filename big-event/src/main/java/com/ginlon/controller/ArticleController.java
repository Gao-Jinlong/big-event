package com.ginlon.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ginlon.pojo.Result;
import com.ginlon.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @GetMapping("/list")
  public Result<String> list(
  // @RequestHeader(name = "Authorization") String token, HttpServletResponse
  // response
  ) {
    // // 验证 token
    // try {
    // Map<String, Object> claims = JwtUtil.parseToken(token);
    // return Result.success();
    // } catch (Exception e) {
    // // TODO: handle exception

    // response.setStatus(401);
    // return Result.error("未登入");
    // }

    return Result.success();
  }

}
