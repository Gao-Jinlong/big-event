package com.ginlon.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginlon.mapper.CategoryMapper;
import com.ginlon.pojo.Category;
import com.ginlon.service.CategoryService;
import com.ginlon.utils.ThreadLocalUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryMapper categoryMapper;

  @Override
  public void add(Category category) {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer id = (Integer) claims.get("id");
    category.setCreateTime(LocalDateTime.now());
    category.setUpdateTime(LocalDateTime.now());
    category.setCreateUser(id);
    categoryMapper.add(category);
  }

  @Override
  public List<Category> list() {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer id = (Integer) claims.get("id");
    return categoryMapper.list(id);
  }
}
