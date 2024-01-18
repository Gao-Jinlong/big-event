package com.ginlon.pojo;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import com.ginlon.annotation.State;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Article {
  private Integer id;
  @NotEmpty
  @Size(min = 1, max = 10)
  private String title;
  @NotEmpty
  private String content;
  @NotEmpty
  @URL
  private String coverImg;
  @State // 自定义校验注解
  private Character state;
  @NotNull
  private Integer categoryId;
  private Integer createUser;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
