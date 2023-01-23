package ru.safronovvladimir.service;

import java.time.LocalDate;
import java.util.List;
import ru.safronovvladimir.model.Animal;
import ru.safronovvladimir.model.TypeAnimal;

public interface AnimalManagementService {

  void addAnimal(String name, LocalDate dateOfBirth, TypeAnimal type);

  void educateCommands(Animal animal, String... command);
}
