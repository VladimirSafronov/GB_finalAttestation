package ru.safronovvladimir.service;

import java.util.List;
import ru.safronovvladimir.model.Animal;

public interface AnimalStoragePort {

  void saveAnimal(Animal animal);

  List<String> getCommands(Animal animal);
}
