package ru.safronovvladimir;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import ru.safronovvladimir.service.AnimalManagementService;
import ru.safronovvladimir.service.AnimalManagementServiceImpl;

public class Config {

  private static Config INSTANCE;
  private AnimalManagementService animalManagementService;

  public static Config get() {
    if (INSTANCE == null) {
      Config config = new Config();

      try (InputStream is = Config.class.getResourceAsStream("/humanFriends.properties")) {
        Properties properties = new Properties();
        properties.load(is);
        config.animalManagementService = new AnimalManagementServiceImpl(properties.getProperty("db.url"),
            properties.getProperty("db.user"),
            properties.getProperty("db.password"));
      } catch (IOException e) {
        throw new IllegalStateException("Failed to load config", e);
      }
      INSTANCE = config;
    }
    return INSTANCE;
  }

  private Config() {
  }

  public AnimalManagementService getAnimalManagementService() {
    return animalManagementService;
  }
}
