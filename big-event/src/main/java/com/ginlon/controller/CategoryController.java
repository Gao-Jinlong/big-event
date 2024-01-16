package com.ginlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ginlon.pojo.Category;
import com.ginlon.pojo.Result;
import com.ginlon.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public Result add(@RequestBody @Validated Category category) {
    categoryService.add(category);
    return Result.success();
  }
}
