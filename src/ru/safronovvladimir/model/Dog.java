package ru.safronovvladimir.model;

import java.time.LocalDate;

public class Dog extends Pet {

  public Dog(String name, LocalDate dateOfBirth, TypeAnimal type) {
    super(name, dateOfBirth, type);
  }
}
