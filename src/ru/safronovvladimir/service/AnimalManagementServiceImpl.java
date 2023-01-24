package ru.safronovvladimir.service;

import java.time.LocalDate;
import java.util.List;
import ru.safronovvladimir.model.Animal;
import ru.safronovvladimir.model.Camel;
import ru.safronovvladimir.model.Cat;
import ru.safronovvladimir.model.Dog;
import ru.safronovvladimir.model.Donkey;
import ru.safronovvladimir.model.Hamster;
import ru.safronovvladimir.model.Horse;
import ru.safronovvladimir.model.TypeAnimal;

public class AnimalManagementServiceImpl implements AnimalManagementService {

  private AnimalStoragePort animalStorage;

  public AnimalManagementServiceImpl() {
    //TODO create AnimalStoragePort
//    AnimalStoragePort animalStorage = new;
  }

  @Override
  public void addAnimal(String name, LocalDate dateOfBirth, TypeAnimal type) {
    //логирование (достаточно саут)
    Animal animal = null;
    switch (type) {
      case CAT:
        animal = new Cat(name, dateOfBirth);
      case DOG:
        animal = new Dog(name, dateOfBirth);
      case HAMSTER:
        animal = new Hamster(name, dateOfBirth);
      case HORSE:
        animal = new Horse(name, dateOfBirth);
      case CAMEL:
        animal = new Camel(name, dateOfBirth);
      case DONKEY:
        animal = new Donkey(name, dateOfBirth);
      default:
        //создать AnimalTypeNotFoundTypeException
    }
    animalStorage.saveAnimal(animal);
  }

  @Override
  public void educateCommands(Animal animal, String... command) {
    //поиск животного в базе
    for (String c : command) {
      animal.addCommand(c);
    }
    //запись животного в БД с новыми командами
  }
}
