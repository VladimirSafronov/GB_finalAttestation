import java.time.LocalDate;
import java.util.List;
import ru.safronovvladimir.Config;
import ru.safronovvladimir.model.Animal;
import ru.safronovvladimir.service.AnimalManagementService;

public class Main {

  public static void main(String[] args) {
    LocalDate exDate = LocalDate.of(2000, 1, 1);
    AnimalManagementService manager = Config.get().getAnimalManagementService();

//    Animal animal = new Animal("WildOldCat", LocalDate.of(2000,1,1), TypeAnimal.CAT);
//    manager.addAnimal(animal);

//    manager.educateCommands(36, "eat");
//
//    List<String> commands = manager.getAllAnimalCommands(2);
//    System.out.println(commands);
//
    List<Animal> animalList = manager.getAllAnimals();
    for (Animal a : animalList) {
      System.out.println(a);
    }

//    manager.delete(36);

//    System.out.println(manager.get(2));

  }
}