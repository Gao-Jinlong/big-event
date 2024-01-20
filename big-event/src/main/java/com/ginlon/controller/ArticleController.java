package com.ginlon.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ginlon.pojo.Article;
import com.ginlon.pojo.PageBean;
import com.ginlon.pojo.Result;
import com.ginlon.service.ArticleService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

  // @GetMapping("/list")
  // public Result<List<?>> list() {
  // return Result.success(articleService.findAll());
  // }

  @GetMapping()
  public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize,
      @RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String state) {
    PageBean articleList = articleService.list(pageNum, pageSize, categoryId, state);
    return Result.success(articleList);
  }

}
