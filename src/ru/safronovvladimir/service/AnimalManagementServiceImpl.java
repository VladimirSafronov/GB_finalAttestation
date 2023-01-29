package ru.safronovvladimir.service;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
              "INSERT INTO actual_animals (name, dateOfBirth, animal_type, commands) VALUES (?,?,?,?)")) {
            ps.setString(1, animal.getName());
            ps.setDate(2, DateUtil.asDate(animal.getDateOfBirth()));
            ps.setString(3, String.valueOf(animal.getType()));
            ps.setString(4, "");
            ps.execute();
          }
          return null;
        });
      }

      public void recordCommands(int id, List<String> commands) {
        sqlHelper.transactionalExecute(conn -> {
          try (PreparedStatement ps = conn.prepareStatement(
              "UPDATE actual_animals SET commands = ? WHERE id = ?")) {
            ps.setString(1, StringUtil.parse(commands));
            ps.setInt(2, id);
            ps.execute();
          }
          return null;
        });
      }
    };
  }

  @Override
  public void addAnimal(Animal animal) {
    LOG.info("addAnimal " + animal.getName());
    animalStorage.saveAnimal(animal);
    Counter.add();
  }

  @Override
  public void educateCommands(int id, String... command) {
    LOG.info("educateCommands id = " + id);
    List<String> animalCommands = getCommands(id);
    animalCommands.addAll(Arrays.asList(command));
    animalStorage.recordCommands(id, animalCommands);
  }

  @Override
  public List<Animal> getAllAnimals() {
    LOG.info("getAllAnimals");
    List<Animal> allAnimals = new ArrayList<>();
    sqlHelper.transactionalExecute(conn -> {
      try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM actual_animals")) {
        try (ResultSet rs = ps.executeQuery()) {
          while (rs.next()) {
            allAnimals.add(getAnimalWithResultSet(rs));
          }
        }
      }
      return null;
    });
    return allAnimals;
  }

  @Override
  public List<String> getAllAnimalCommands(int id) {
    LOG.info("getAllCommands id = " + id);
    return sqlHelper.transactionalExecute(conn -> {
      List<String> commands;
      try (PreparedStatement ps = conn.prepareStatement(
          "SELECT * FROM actual_animals WHERE id = ?")) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
          if (!rs.next()) {
            LOG.warning("There isn't animal with id " + id);
            throw new NotExistAnimalException();
          }
          commands = StringUtil.toList(rs.getString("commands"));
        }
      }
      return commands;
    });
  }

  @Override
  public void delete(int id) {
    LOG.info("delete animal with id = " + id);
    sqlHelper.execute("DELETE FROM actual_animals WHERE id = ?", ps -> {
      ps.setInt(1, id);
      if (ps.executeUpdate() == 0) {
        throw new NotExistAnimalException();
      }
      return null;
    });
  }

  @Override
  public Animal get(int id) {
    LOG.info("get animal with id = " + id);
    return sqlHelper.transactionalExecute(conn -> {
      try (PreparedStatement ps = conn.prepareStatement(
          "SELECT * FROM actual_animals WHERE id = ?")) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
          if (!rs.next()) {
            LOG.warning("There isn't animal with id " + id);
            throw new NotExistAnimalException();
          }
          return getAnimalWithResultSet(rs);
        }
      }
    });
  }

  private List<String> getCommands(int id) {
    List<String> animalCommands = getAllAnimalCommands(id);
    if (animalCommands.get(0).equals("")) {
      animalCommands.remove(0);
    }
    return animalCommands;
  }

  private Animal getAnimalWithResultSet(ResultSet rs) {
    Animal animal;
    try {
      animal = new Animal(
          rs.getString("name"),
          LocalDate.parse(rs.getString("dateOfBirth")),
          TypeAnimal.valueOf(rs.getString("animal_type").toUpperCase()));
      int animalId = rs.getInt("id");
      animal.setId(animalId);
      animal.setCommands(getAllAnimalCommands(animalId));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return animal;
  }
}
