package com.github.edgar615.validation.jsr303;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProhibitedConstraintValidator implements ConstraintValidator<ProhibitedValidator, Object> {

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return true;
    }
    return false;
  }
}
