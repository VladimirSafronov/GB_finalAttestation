package ru.safronovvladimir;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;
import ru.safronovvladimir.service.AnimalManagementService;
import ru.safronovvladimir.service.AnimalManagementServiceImpl;

public class Config {

  private static Config INSTANCE;
  private static AnimalManagementService animalManagementService;

  public static Config get() {
    if (INSTANCE == null) {
      Properties properties = new Properties();
      File fileWithProperties = new File(getHomeDir(), "config/humanFriends.properties");

      try (InputStream is = Files.newInputStream(fileWithProperties.toPath())) {
        properties.load(is);
        animalManagementService = new AnimalManagementServiceImpl(properties.getProperty("db.url"),
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

  public static AnimalManagementService getAnimalManagementService() {
    return animalManagementService;
  }

  private static File getHomeDir() {
    String prop = System.getProperty("homeDir");
    File homeDir = new File(prop != null ? prop : ".");
    if (!homeDir.isDirectory()) {
      throw new IllegalStateException(homeDir + " isn't directory!");
    }
    return homeDir;
  }
}
