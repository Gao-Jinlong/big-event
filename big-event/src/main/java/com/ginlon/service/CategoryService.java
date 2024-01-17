package com.ginlon.service;

import java.util.List;

import com.ginlon.pojo.Category;

public interface CategoryService {

  void add(Category category);

  List<Category> list();

}
