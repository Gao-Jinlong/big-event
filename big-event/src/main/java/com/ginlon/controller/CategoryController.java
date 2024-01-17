package com.ginlon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ginlon.pojo.Category;
import com.ginlon.pojo.Result;
import com.ginlon.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
    categoryService.add(category);
    return Result.success();
  }

  @GetMapping
  public Result<List<Category>> list() {
    List<Category> categories = categoryService.list();

    return Result.success(categories);
  }

  @GetMapping("/detail")
  public Result<Category> detail(Integer id) {
    Category category = categoryService.detail(id);
    return Result.success(category);
  }

  @PutMapping
  public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
    categoryService.update(category);
    return Result.success();
  }

  @DeleteMapping
  public Result delete(Integer id) {
    categoryService.delete(id);
    return Result.success();
  }
}
