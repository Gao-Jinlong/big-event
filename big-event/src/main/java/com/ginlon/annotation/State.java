package com.ginlon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ginlon.validation.StateValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented // 元注解 生成javadoc时会包含注解
@Target({ ElementType.FIELD, }) // 元注解 限制注解的使用范围
@Retention(RetentionPolicy.RUNTIME) // 元注解 限制注解的生命周期
@Constraint(validatedBy = { StateValidation.class }) // 指定校验器
public @interface State {

  // 校验失败时的提示信息
  String message() default "{state must be 0 or 1}";

  // 分组校验
  Class<?>[] groups() default {};

  // 负载 获取注解的附加信息
  Class<? extends Payload>[] payload() default {};
}
