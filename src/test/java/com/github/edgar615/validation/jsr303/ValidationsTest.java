package com.github.edgar615.validation.jsr303;

import org.junit.Test;

public class ValidationsTest {

  @Test
  public void testDate() {
    User user = new User();
    user.setDate("2020-01-10");
    user.setDate2("20200110");
    user.setDateTime("2020-01-10 15:48:10");
    user.setDateTime2("2020-01-10 15:48");
    user.setTime("15:48:10");
    user.setTime2("15:48");
    Validations.validateBean(user);
  }
}
