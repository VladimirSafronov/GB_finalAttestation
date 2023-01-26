package ru.safronovvladimir.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

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

  public <T> T transactionalExecute(SqlTransaction<T> executor) {
    try (Connection conn = connectionFactory.getConnection()) {
      Savepoint savepoint = conn.setSavepoint("savepoint");
      try {
        conn.setAutoCommit(false);
        T res = executor.execute(conn);
        conn.commit();
        return res;
      } catch (SQLException e) {
        conn.rollback(savepoint);
        System.out.println(e.getMessage());
        throw e;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}