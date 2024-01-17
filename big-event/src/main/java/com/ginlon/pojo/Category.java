package com.ginlon.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class Category {
  @NotNull(groups = Update.class, message = "分类id不能为空")
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

  // 校验分组
  // 没有指定分组的校验项，默认属于 Default 分组
  // 分组之间可以继承 如：Update extends Add
  public interface Add extends Default {
  }

  public interface Update extends Default {
  }
}
