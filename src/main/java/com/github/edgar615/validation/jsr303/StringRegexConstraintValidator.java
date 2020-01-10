package com.github.edgar615.validation.jsr303;

import com.google.common.base.Strings;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringRegexConstraintValidator {

  public boolean match(String value, Pattern pattern) {
    if (Strings.isNullOrEmpty(value)) {
      return true;
    }
    try {
      Matcher matcher = pattern.matcher(value);
      return matcher.matches();
    } catch (Exception e) {
      return false;
    }
  }
}
