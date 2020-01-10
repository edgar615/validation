package com.github.edgar615.validation.jsr303;

import com.google.common.base.Strings;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeConstraintValidator implements ConstraintValidator<TimeValidator, String> {

  private String timeFormat;

  @Override
  public void initialize(TimeValidator constraintAnnotation) {
    this.timeFormat = constraintAnnotation.format();
  }


  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (Strings.isNullOrEmpty(value)) {
      return true;
    }
    try {
      LocalTime.parse(value, DateTimeFormatter.ofPattern(timeFormat));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
