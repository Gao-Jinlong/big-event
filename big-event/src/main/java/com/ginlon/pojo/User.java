package com.ginlon.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // lombok注解，自动生成getter/setter方法
public class User {
  @NotNull(message = "id不能为空")
  private Integer id;
  private String username;
  @JsonIgnore // 不返回给前端
  private String password;
  @NotEmpty(message = "昵称不能为空")
  @Pattern(regexp = "^.{1,10}$", message = "昵称必须是1-10位")
  private String nickname;

  @NotEmpty(message = "邮箱不能为空")
  @Email(message = "邮箱格式不正确")
  private String email;
  private String userPic;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
