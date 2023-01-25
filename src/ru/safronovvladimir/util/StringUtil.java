package ru.safronovvladimir.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

  public static String parse(List<String> list) {
    StringBuilder sb = new StringBuilder();
    for (String str : list) {
      sb.append(str).append(", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    return sb.toString();
  }

  public static List<String> toList(String str) {
    return new ArrayList<>(Arrays.asList(str.split(", ")));
  }
}
