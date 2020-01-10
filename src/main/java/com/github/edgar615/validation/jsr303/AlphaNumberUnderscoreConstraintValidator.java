package com.github.edgar615.validation.jsr303;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaNumberUnderscoreConstraintValidator extends StringRegexConstraintValidator implements ConstraintValidator<AlphaNumberUnderscoreValidator, String> {

  private static final Pattern PATTERN = Pattern.compile("[0-9A-Za-z_]*");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return match(value, PATTERN);
  }
}
