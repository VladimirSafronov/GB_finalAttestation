package ru.safronovvladimir.model;

import java.time.LocalDate;

public class Cat extends Pet {

  public Cat(String name, LocalDate dateOfBirth, TypeAnimal type) {
    super(name, dateOfBirth, type);
  }
}
