package com.github.edgar615.validation.jsr303;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaNumberConstraintValidator extends StringRegexConstraintValidator implements
    ConstraintValidator<AlphaNumberValidator, String> {

  private static final Pattern PATTERN = Pattern.compile("[0-9A-Za-z]*");


  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return match(value, PATTERN);
  }
}
