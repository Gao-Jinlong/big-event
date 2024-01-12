package com.ginlon.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ginlon.pojo.Result;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @GetMapping("/list")
  public Result<String> list() {
    return Result.success();
  }

}
