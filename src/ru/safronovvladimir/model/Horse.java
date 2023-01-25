package ru.safronovvladimir.model;

import java.time.LocalDate;

public class Horse extends PackAnimal {

  public Horse(String name, LocalDate dateOfBirth, TypeAnimal type) {
    super(name, dateOfBirth, type);
  }
}
