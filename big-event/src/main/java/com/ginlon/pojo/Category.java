package com.ginlon.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Category {
  private Integer id;
  @NotEmpty(message = "分类名称不能为空")
  private String categoryName;
  @NotEmpty(message = "分类别名不能为空")
  private String categoryAlias;
  private Integer createUser;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime;
}
