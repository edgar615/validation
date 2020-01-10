package com.github.edgar615.validation.jsr303;

public class User {

  @DateValidator
  private String date;

  @DateValidator(format = "yyyyMMdd")
  private String date2;

  @DateTimeValidator
  private String dateTime;

  @DateTimeValidator(format = "yyyy-MM-dd HH:mm")
  private String dateTime2;

  @TimeValidator
  private String time;

  @TimeValidator(format = "HH:mm")
  private String time2;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDate2() {
    return date2;
  }

  public void setDate2(String date2) {
    this.date2 = date2;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public String getDateTime2() {
    return dateTime2;
  }

  public void setDateTime2(String dateTime2) {
    this.dateTime2 = dateTime2;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTime2() {
    return time2;
  }

  public void setTime2(String time2) {
    this.time2 = time2;
  }
}
