package com.ginlon.pojo;

import java.time.LocalDateTime;

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
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
