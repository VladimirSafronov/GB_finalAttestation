import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import ru.safronovvladimir.Config;
import ru.safronovvladimir.model.Animal;
import ru.safronovvladimir.model.TypeAnimal;
import ru.safronovvladimir.service.AnimalManagementService;
import ru.safronovvladimir.service.AnimalManagementServiceImpl;
import ru.safronovvladimir.util.StringUtil;

public class Main {

  public static void main(String[] args) {
    LocalDate exDate = LocalDate.of(2000, 1, 1);
    AnimalManagementService manager = Config.get().getAnimalManagementService();

//    Animal animal = new Animal("WildOldCat", LocalDate.of(2000,1,1), TypeAnimal.CAT);
//    manager.addAnimal(animal);

    manager.educateCommands(36, "eat");

    List<String> commands = manager.getAllCommands(36);
    System.out.println(commands);

  }
}