package com.ginlon.pojo;

import java.time.LocalDateTime;
import lombok.Data;

@Data // lombok注解，自动生成getter/setter方法
public class User {
  private Integer id;
  private String username;
  private String password;
  private String nickname;
  private String email;
  private String userPic;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
