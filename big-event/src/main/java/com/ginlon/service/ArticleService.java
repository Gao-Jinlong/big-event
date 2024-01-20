package com.ginlon.service;

import java.util.List;

import com.ginlon.pojo.Article;
import com.ginlon.pojo.PageBean;

public interface ArticleService {

  void add(Article article);

  PageBean list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

}
