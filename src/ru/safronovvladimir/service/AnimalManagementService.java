package ru.safronovvladimir.service;

import java.util.List;
import ru.safronovvladimir.model.Animal;

public interface AnimalManagementService {

  void addAnimal(Animal animal);

  void educateCommands(int id, String... command);

  List<Animal> getAllAnimals();

  List<String> getAllAnimalCommands(int id);

}
