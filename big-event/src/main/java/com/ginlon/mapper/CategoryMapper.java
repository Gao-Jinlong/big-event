package com.ginlon.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ginlon.pojo.Category;

@Mapper
public interface CategoryMapper {

  @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)" +
      " values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
  void add(Category category);
}