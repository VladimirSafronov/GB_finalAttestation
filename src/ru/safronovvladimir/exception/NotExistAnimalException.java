package ru.safronovvladimir.exception;

public class NotExistAnimalException extends RuntimeException {

  public NotExistAnimalException() {
    super("There isn't animal with this name!");
  }
}
