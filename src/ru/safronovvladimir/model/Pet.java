package ru.safronovvladimir.model;

import java.time.LocalDate;

public class Pet extends Animal {

  public Pet(String name, LocalDate dateOfBirth) {
    super(name, dateOfBirth);
  }
}
