package com.ginlon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ginlon.pojo.Article;

@Mapper
public interface ArticleMapper {

  @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time)" +
      "value(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
  void add(Article article);

  @Select("select * from article")
  List<Article> findAll();
}
