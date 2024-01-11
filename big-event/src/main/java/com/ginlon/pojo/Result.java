package com.ginlon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
  private Integer code;
  private String message;
  private T data;

  public static <E> Result<E> success(E data) {
    return new Result<>(200, "success", data);
  }

  public static Result<String> success() {
    return new Result<String>(200, "success", null);
  }

  public static Result<String> error(String message) {
    return new Result<String>(500, message, null);
  }
}
