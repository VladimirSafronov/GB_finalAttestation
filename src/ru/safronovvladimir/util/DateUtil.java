package ru.safronovvladimir.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtil {

  public static Date asDate(LocalDate localDate) {
    return Date.valueOf(localDate);
  }

  public static LocalDate toLocalDate(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
