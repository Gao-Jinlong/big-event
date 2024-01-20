package com.ginlon.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginlon.mapper.ArticleMapper;
import com.ginlon.pojo.Article;
import com.ginlon.pojo.PageBean;
import com.ginlon.service.ArticleService;
import com.ginlon.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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
  public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
    PageBean<Article> pageBean = new PageBean<>();

    PageHelper.startPage(pageNum, pageSize);

    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer userId = (Integer) claims.get("id");

    List<Article> articleList = articleMapper.list(userId, categoryId, state);

    // Page 中提供了方法，可以获取 PageHelper 分页查询后的结果集
    Page<Article> page = (Page<Article>) articleList;

    pageBean.setItems(page.getResult());
    pageBean.setTotal(page.getTotal());

    return pageBean;
  }
}
