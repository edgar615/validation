package com.github.edgar615.validation.jsr303;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaSpaceConstraintValidator extends StringRegexConstraintValidator implements
    ConstraintValidator<AlphaSpaceValidator, String> {

  private static final Pattern PATTERN = Pattern.compile("[A-Za-z\\s]*");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return match(value, PATTERN);
  }
}
