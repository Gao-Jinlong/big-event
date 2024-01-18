package com.ginlon.service;

import java.util.List;

import com.ginlon.pojo.Article;

public interface ArticleService {

  void add(Article article);

  List<Article> findAll();

}
