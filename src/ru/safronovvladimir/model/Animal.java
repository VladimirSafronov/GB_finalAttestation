package ru.safronovvladimir.model;

import com.sun.istack.internal.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal {

  private int id;
  private String name;
  private LocalDate dateOfBirth;
  private List<String> commands;
  private TypeAnimal type;

  public Animal() {
  }

  public Animal(String name, LocalDate dateOfBirth, TypeAnimal type) {
    Objects.requireNonNull(name, "name mustn't be null");
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.commands = new ArrayList<>();
    this.type = type;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public void setCommands(List<String> commands) {
    this.commands = commands;
  }

  public TypeAnimal getType() {
    return type;
  }

  public void setType(TypeAnimal type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Animal{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", commands=" + commands +
        ", type=" + type +
        '}';
  }
}
