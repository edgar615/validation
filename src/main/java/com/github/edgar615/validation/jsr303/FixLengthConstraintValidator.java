package com.github.edgar615.validation.jsr303;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FixLengthConstraintValidator implements
    ConstraintValidator<FixLengthValidator, String> {

  private int length;

  @Override
  public void initialize(FixLengthValidator constraintAnnotation) {
    this.length = constraintAnnotation.length();
  }


  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return false;
    }
    try {
      return value.length() == length;
    } catch (Exception e) {
      return false;
    }
  }
}
