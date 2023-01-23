package ru.safronovvladimir.service;

import java.util.List;
import ru.safronovvladimir.model.Animal;

public interface AnimalStoragePort {

  List<String> getCommands(Animal animal);
}
