package ru.safronovvladimir.model;

import java.time.LocalDate;

public class Donkey extends PackAnimal {

  public Donkey(String name, LocalDate dateOfBirth, TypeAnimal type) {
    super(name, dateOfBirth, type);
  }
}
