package ru.safronovvladimir.sql;

import java.sql.DriverManager;

public class DBService {

  private final SqlHelper sqlHelper;

  public DBService(String dbUrl, String dbUser, String dbPassword) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException(e);
    }
    sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
  }
}
