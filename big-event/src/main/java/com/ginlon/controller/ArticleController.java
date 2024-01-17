package com.ginlon.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ginlon.pojo.Article;
import com.ginlon.pojo.Result;
import com.ginlon.service.ArticleService;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @PostMapping
  public Result addResult(@RequestBody @Validated Article article) {
    articleService.add(article);
    return Result.success();
  }

  @GetMapping("/list")
  public Result<String> list(

  ) {

    return Result.success();
  }

}
