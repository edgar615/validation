package com.github.edgar615.validation.jsr303;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaConstraintValidator extends StringRegexConstraintValidator implements
    ConstraintValidator<AlphaValidator, String> {

  private static final Pattern PATTERN = Pattern.compile("[a-zA-Z]*");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return match(value, PATTERN);
  }
}
