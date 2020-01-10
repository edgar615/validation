package com.github.edgar615.validation.jsr303;

import com.google.common.base.Strings;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<DateValidator, String> {

  private String dateFormat;

  @Override
  public void initialize(DateValidator constraintAnnotation) {
    this.dateFormat = constraintAnnotation.format();
  }


  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (Strings.isNullOrEmpty(value)) {
      return true;
    }
    try {
      LocalDate.parse(value, DateTimeFormatter.ofPattern(dateFormat));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
