package com.ginlon.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginlon.mapper.ArticleMapper;
import com.ginlon.pojo.Article;
import com.ginlon.service.ArticleService;
import com.ginlon.utils.ThreadLocalUtil;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ArticleMapper articleMapper;

  @Override
  public void add(Article article) {
    article.setCreateTime(LocalDateTime.now());
    article.setUpdateTime(LocalDateTime.now());

    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer id = (Integer) claims.get("id");
    article.setCreateUser(id);

    articleMapper.add(article);
  }

  @Override
  public List<Article> findAll() {
    return articleMapper.findAll();
  }
}
