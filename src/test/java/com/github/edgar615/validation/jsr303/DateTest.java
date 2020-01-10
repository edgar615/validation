package com.github.edgar615.validation.jsr303;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

public class DateTest {

  @Test
  public void testParse() {
    LocalDateTime localDateTime =  LocalDateTime.parse("2020-01-10 15:48:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    System.out.println(localDateTime);

    LocalTime localTime =  LocalTime.parse("15:48:10", DateTimeFormatter.ofPattern("HH:mm:ss"));
    System.out.println(localTime);

    LocalDate localDate =  LocalDate.parse("2020-01-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    System.out.println(localDateTime);
  }
}
