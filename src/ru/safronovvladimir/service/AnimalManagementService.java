package ru.safronovvladimir.service;

import java.time.LocalDate;
import java.util.List;
import ru.safronovvladimir.model.Animal;
import ru.safronovvladimir.model.TypeAnimal;

public interface AnimalManagementService {

  void addAnimal(Animal animal);

  void educateCommands(int id, String... command);

  List<String> getAllCommands(int id);

}
