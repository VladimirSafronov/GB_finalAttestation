package ru.safronovvladimir.service;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import ru.safronovvladimir.exception.*;
import ru.safronovvladimir.model.*;
import ru.safronovvladimir.sql.SqlHelper;
import ru.safronovvladimir.util.*;

public class AnimalManagementServiceImpl implements AnimalManagementService {

  private static final Logger LOG = Logger.getLogger(AnimalManagementServiceImpl.class.getName());
  private final AnimalStoragePort animalStorage;
  private final SqlHelper sqlHelper;

  public AnimalManagementServiceImpl(String dbUrl, String dbUser, String dbPassword) {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException(e);
    }
    sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    animalStorage = new AnimalStoragePort() {
      @Override
      public void saveAnimal(Animal animal) {
        sqlHelper.transactionalExecute(conn -> {
          try (PreparedStatement ps = conn.prepareStatement(
              "INSERT INTO actual_animals (name, dateOfBirth, commands, animal_type) VALUES (?,?,?,?)")) {
            ps.setString(1, animal.getName());
            ps.setDate(2, (Date) DateUtil.asDate(animal.getDateOfBirth()));
            ps.setString(3, StringUtil.parse(animal.getCommands()));
            ps.setString(4, String.valueOf(animal.getType()));
          }
          return null;
        });
      }

      @Override
      public void writeCommands(String name, List<String> commands) {
        sqlHelper.transactionalExecute(conn -> {
          try (PreparedStatement ps = conn.prepareStatement(
              "INSERT INTO actual_animals (commands) WHERE name = " + name + " VALUES (?)")) {
            ps.setString(1, StringUtil.parse(commands));
          }
          return null;
        });
      }
    };
  }

  @Override
  public void addAnimal(String name, LocalDate dateOfBirth, TypeAnimal type) {
    LOG.info("addAnimal " + name);
    Animal animal;
    switch (type) {
      case CAT:
        animal = new Cat(name, dateOfBirth, type);
      case DOG:
        animal = new Dog(name, dateOfBirth, type);
      case HAMSTER:
        animal = new Hamster(name, dateOfBirth, type);
      case HORSE:
        animal = new Horse(name, dateOfBirth, type);
      case CAMEL:
        animal = new Camel(name, dateOfBirth, type);
      case DONKEY:
        animal = new Donkey(name, dateOfBirth, type);
      default:
        LOG.warning(type + " - this type isn't exist");
        throw new AnimalTypeNotFoundTypeException();
    }
//    animalStorage.saveAnimal(animal);
  }

  @Override
  public void educateCommands(String name, String... command) {
    LOG.info("educateCommands " + name);
    List<String> animalCommands = getAllCommands(name);
    if (animalCommands.get(0).equals("")) {
      animalCommands.remove(0);
    }
    animalCommands.addAll(Arrays.asList(command));
    animalStorage.writeCommands(name, animalCommands);
  }

  @Override
  public List<String> getAllCommands(String name) {
    LOG.info("getAllCommands " + name);
    return sqlHelper.transactionalExecute(conn -> {
      List<String> commands;
      try (PreparedStatement ps = conn.prepareStatement(
          "SELECT * FROM actual_animals WHERE name = ?")) {
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
          LOG.warning("There isn't animal with name " + name);
          throw new NotExistAnimalException();
        }
        commands = StringUtil.toList(rs.getString("commands"));
      }
      return commands;
    });
  }
}
