package com.ginlon.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ginlon.pojo.User;

@Mapper
public interface UserMapper {

  @Select("select * from user where username = #{username}")
  User findByUsername(String username);

  @Insert("insert into user(username, password, create_time, update_time)" +
      " values(#{username}, #{password}, now(), now())")
  void add(String username, String password);

  @Update("update user set nickname = #{nickname}, email=#{email}, update_time=#{updateTime} where id=#{id}")
  void update(User user);

  @Update("update user set avatar=#{avatarUrl}, update_time=now() where id=#{id}")
  void updateAvatar(String avatarUrl, Integer id);

  @Update("update user set password=#{md5String}, update_time=now() where id=#{id}")
  void updatePassword(String md5String, Integer id);
}
