package ru.safronovvladimir.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal {

  private String name;
  private LocalDate dateOfBirth;
  private final List<String> commands;

  public Animal(String name, LocalDate dateOfBirth) {
    Objects.requireNonNull(name, "name mustn't be null");
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.commands = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<String> getCommands() {
    return commands;
  }

  public void addCommands(String command) {
    commands.add(command);
  }

  @Override
  public String toString() {
    return "Animal{" +
        "name='" + name + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", commands=" + commands +
        '}';
  }
}
