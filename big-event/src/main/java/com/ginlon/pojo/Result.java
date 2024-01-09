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

  public static Result success() {
    return new Result(200, "success", null);
  }

  public static Result error(String message) {
    return new Result(500, message, null);
  }
}
