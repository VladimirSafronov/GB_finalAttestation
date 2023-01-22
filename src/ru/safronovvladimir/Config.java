package ru.safronovvladimir;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;
import ru.safronovvladimir.sql.DBService;

public class Config {

  private static Config INSTANCE;
  private static DBService dbService;

  public static Config get() {
    if (INSTANCE == null) {
      Properties properties = new Properties();
      File fileWithProperties = new File("config/resumes.properties");

      try (InputStream is = Files.newInputStream(fileWithProperties.toPath())) {
        properties.load(is);
        dbService = new DBService(properties.getProperty("db.url"),
            properties.getProperty("db.user"),
            properties.getProperty("db.password"));
      } catch (IOException e) {
        throw new IllegalStateException(
            "Invalid config file " + fileWithProperties.getAbsolutePath());
      }
      INSTANCE = new Config();
    }
    return INSTANCE;
  }

  private Config() {
  }

  public static DBService getDbService() {
    return dbService;
  }
}
