package com.ginlon.validation;

import com.ginlon.annotation.State;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, Character> {

  /**
   * @param value   被校验的值
   * @param context 约束校验器执行上下文
   */
  @Override
  public boolean isValid(Character value, ConstraintValidatorContext context) {
    // 校验规则
    if (value == null) {
      return false;
    }
    if (value.equals('0') || value.equals('1')) {
      return true;
    }
    return false;
  }
}
