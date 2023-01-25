package ru.safronovvladimir.exception;

public class AnimalTypeNotFoundTypeException extends RuntimeException {

  public AnimalTypeNotFoundTypeException() {
    super("This type of animal isn't found");
  }
}
