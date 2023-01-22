package ru.safronovvladimir.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

  private final ConnectionFactory connectionFactory;

  public SqlHelper(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public void execute(String sql) {
    execute(sql, PreparedStatement::execute);
  }

  public <T> T execute(String sql, SqlExecutor<T> sqlExecutor) {
    try (Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      return sqlExecutor.execute(ps);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}