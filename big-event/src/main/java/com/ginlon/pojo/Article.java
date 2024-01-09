package com.ginlon.pojo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Article {
  private Integer id;
  private String title;
  private String content;
  private String coverImg;
  private Character state;
  private Integer categoryId;
  private Integer createUser;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
